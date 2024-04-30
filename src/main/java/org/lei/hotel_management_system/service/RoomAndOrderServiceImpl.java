package org.lei.hotel_management_system.service;

import jakarta.persistence.criteria.Predicate;
import org.lei.hotel_management_system.entity.Order;
import org.lei.hotel_management_system.enums.Status;
import org.lei.hotel_management_system.repository.OrderRepository;
import org.lei.hotel_management_system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Service
public class RoomAndOrderServiceImpl implements RoomAndOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<String> findUnavailableRoomNumbersByDates(String checkInDate, String checkOutDate) {
        if (checkInDate == null || checkInDate.isEmpty() || checkOutDate == null || checkOutDate.isEmpty()) {
            return Collections.emptyList();
        }

        Specification<Order> spec = (root, query, cb) -> {
            Predicate statusPredicate = root.get("status").in(Status.CREATED, Status.CHECKED);
            Predicate overlapPredicate = cb.or(
                    cb.between(root.get("checkInDate"), Date.valueOf(checkInDate), Date.valueOf(checkOutDate)),
                    cb.between(root.get("checkOutDate"), Date.valueOf(checkInDate), Date.valueOf(checkOutDate)),
                    cb.and(
                            cb.lessThan(root.get("checkInDate"), Date.valueOf(checkInDate)),
                            cb.greaterThan(root.get("checkOutDate"), Date.valueOf(checkOutDate))
                    )
            );
            return cb.and(statusPredicate, overlapPredicate);
        };

        return orderRepository.findAll(spec).stream().map(Order::getRoomNumber).distinct().toList();
    }
}
