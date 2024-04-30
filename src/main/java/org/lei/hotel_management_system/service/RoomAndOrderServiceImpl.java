package org.lei.hotel_management_system.service;

import jakarta.persistence.criteria.Predicate;
import org.lei.hotel_management_system.entity.Order;
import org.lei.hotel_management_system.repository.OrderRepository;
import org.lei.hotel_management_system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomAndOrderServiceImpl implements RoomAndOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<String> findUnavailableRoomNumbersByDates(String checkInDate, String checkOutDate) {
        return orderRepository.findAll((Specification<Order>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (checkInDate != null && !checkInDate.isEmpty()) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("checkInDate"), Date.valueOf(checkInDate)));
            }

            if (checkOutDate != null && !checkOutDate.isEmpty()) {
                predicates.add(cb.lessThanOrEqualTo(root.get("checkOutDate"), Date.valueOf(checkOutDate)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(Order::getRoomNumber).toList();
    }
}
