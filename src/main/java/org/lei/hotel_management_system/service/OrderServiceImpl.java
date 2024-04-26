package org.lei.hotel_management_system.service;

import jakarta.persistence.criteria.*;
import org.lei.hotel_management_system.DTO.OrderCreateDTO;
import org.lei.hotel_management_system.DTO.OrderDetailsDTO;
import org.lei.hotel_management_system.DTO.OrderUpdateDTO;
import org.lei.hotel_management_system.entity.Order;
import org.lei.hotel_management_system.enums.Status;
import org.lei.hotel_management_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RoomService roomService;

    @Override
    public Order addOrder(Order order) {
        if (roomService.getByRoomNumber(order.getRoomNumber()) == null)
            throw new RuntimeException("Room number not exist!");
        return orderRepository.save(order);
    }

    @Override
    public void updateOrder(OrderUpdateDTO updateOrder) {
        Order order = orderRepository.findByOrderNumber(updateOrder.getOrderNumber());
        if (order == null) {
            throw new RuntimeException("Order number does not exist");
        }
        if (updateOrder.getCustomerName() != null) order.setCustomerName(updateOrder.getCustomerName());
        if (updateOrder.getCustomerEmail() != null) order.setCustomerEmail(updateOrder.getCustomerEmail());
        if (updateOrder.getStatus() != null) order.setStatus(updateOrder.getStatus());
        orderRepository.save(order);
    }

    @Override
    public void checkin(String orderNumber) {
        OrderUpdateDTO updateOrder = new OrderUpdateDTO();
        updateOrder.setOrderNumber(orderNumber);
        updateOrder.setStatus(Status.CHECKEDIN);
        updateOrder(updateOrder);
    }

    @Override
    public void checkout(String orderNumber) {
        OrderUpdateDTO updateOrder = new OrderUpdateDTO();
        updateOrder.setOrderNumber(orderNumber);
        updateOrder.setStatus(Status.FINISHED);
        updateOrder(updateOrder);
    }

    @Override
    public void cancel(String orderNumber) {
        OrderUpdateDTO updateOrder = new OrderUpdateDTO();
        updateOrder.setOrderNumber(orderNumber);
        updateOrder.setStatus(Status.CANCELED);
        updateOrder(updateOrder);
    }

    @Override
    public List<OrderDetailsDTO> findAllOrders() {
        return orderRepository.findAll().stream().map(this::convertOrderToOrderDetailsDTO).toList();
    }

    @Override
    public List<OrderDetailsDTO> list(String orderNumber, String customerName, String customerEmail, String status) {
        return orderRepository.findAll((Specification<Order>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (orderNumber != null && !orderNumber.isEmpty()) {
                predicates.add(cb.equal(root.get("orderNumber"), orderNumber));
            }

            if (customerName != null && !customerName.isEmpty()) {
                predicates.add(cb.like(root.get("customerName"), "%" + customerName + "%"));
            }

            if (customerEmail != null && !customerEmail.isEmpty()) {
                predicates.add(cb.equal(root.get("customerEmail"), customerEmail));
            }

            if (status != null && !status.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(this::convertOrderToOrderDetailsDTO).toList();
    }


    @Override
    public Order convertOrderDTOToOrder(OrderCreateDTO orderCreateDTO) {
        return new Order(
                orderCreateDTO.getRoomNumber(),
                orderCreateDTO.getCustomerName(),
                orderCreateDTO.getCustomerEmail(),
                LocalDate.parse(orderCreateDTO.getCheckInDate()),
                LocalDate.parse(orderCreateDTO.getCheckOutDate()),
                generateOrderNumber()
        );
    }

    @Override
    public OrderDetailsDTO convertOrderToOrderDetailsDTO(Order order) {
        return new OrderDetailsDTO(
                order.getRoomNumber(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getCheckInDate(),
                order.getCheckOutDate(),
                order.getStatus(),
                order.getOrderNumber(),
                order.getCreatedAt()
        );
    }

    private String generateOrderNumber() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000);
        String orderNumber = timestamp + randomNum;
        while (orderRepository.findByOrderNumber(orderNumber) != null) {
            orderNumber = generateOrderNumber();
        }
        return orderNumber;
    }
}
