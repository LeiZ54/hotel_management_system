package org.lei.hotel_management_system.service;

import jakarta.persistence.criteria.*;
import org.lei.hotel_management_system.DTO.OrderCreateDTO;
import org.lei.hotel_management_system.DTO.OrderDetailsDTO;
import org.lei.hotel_management_system.DTO.OrderListDTO;
import org.lei.hotel_management_system.DTO.OrderUpdateDTO;
import org.lei.hotel_management_system.entity.Order;
import org.lei.hotel_management_system.entity.User;
import org.lei.hotel_management_system.enums.Role;
import org.lei.hotel_management_system.enums.Status;
import org.lei.hotel_management_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    @Autowired
    UserService userService;

    @Override
    public Order addOrder(Order order) {
        if (roomService.getByRoomNumber(order.getRoomNumber()) == null)
            throw new RuntimeException("Room number does not exist!");
        if (!checkOrderAvailable(order.getRoomNumber(), order.getCheckInDate(), order.getCheckOutDate()))
            throw new RuntimeException("This room is not available for this time!");
        order.setUsername(userService.getCurrentUser().getUsername());
        return orderRepository.save(order);
    }

    @Override
    public void updateOrder(OrderUpdateDTO updateOrder) {
        Order order = orderRepository.findByOrderNumber(updateOrder.getOrderNumber());
        if (order == null) throw new RuntimeException("Order number does not exist");
        if (updateOrder.getCustomerName() != null) order.setCustomerName(updateOrder.getCustomerName());
        if (updateOrder.getCustomerEmail() != null) order.setCustomerEmail(updateOrder.getCustomerEmail());
        if (updateOrder.getStatus() != null) order.setStatus(updateOrder.getStatus());
        orderRepository.save(order);
    }

    @Override
    public void checkin(String orderNumber) {
        OrderUpdateDTO updateOrder = new OrderUpdateDTO();
        updateOrder.setOrderNumber(orderNumber);
        updateOrder.setStatus(Status.CHECKED);
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
    public List<OrderListDTO> list(String orderNumber, String roomNumber, String username, String customerName, String customerEmail, String status, String checkInDate, String checkOutDate, Integer page) {
        return orderRepository.findAll((Specification<Order>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (orderNumber != null && !orderNumber.isEmpty()) {
                predicates.add(cb.equal(root.get("orderNumber"), orderNumber));
            }

            if (roomNumber != null && !roomNumber.isEmpty()) {
                predicates.add(cb.equal(root.get("roomNumber"), roomNumber));
            }

            if (userService.getCurrentUser().getRole().equals(Role.CUSTOMER)) {
                predicates.add(cb.equal(root.get("username"), userService.getCurrentUser().getUsername()));
            }
            if (username != null && !username.isEmpty()) {
                predicates.add(cb.equal(root.get("username"), username));
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

            if (checkInDate != null && !checkInDate.isEmpty()) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("checkInDate"), Date.valueOf(checkInDate)));
            }

            if (checkOutDate != null && !checkOutDate.isEmpty()) {
                predicates.add(cb.lessThanOrEqualTo(root.get("checkOutDate"), Date.valueOf(checkOutDate)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, PageRequest.of(page, 10)).stream().map(this::convertOrderToOrderListDTO).toList();
    }

    @Override
    public OrderDetailsDTO getOrderDetails(String orderNumber) {
        User currentUser = userService.getCurrentUser();
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (currentUser.getRole().equals(Role.CUSTOMER) && !order.getUsername().equals(currentUser.getUsername()))
            throw new RuntimeException("You are not allowed to access this order");
        return convertOrderToOrderDetailsDTO(order);
    }


    @Override
    public Order convertOrderDTOToOrder(OrderCreateDTO orderCreateDTO) {
        return new Order(orderCreateDTO.getRoomNumber(), userService.getCurrentUser().getUsername(), orderCreateDTO.getCustomerName(), orderCreateDTO.getCustomerEmail(), LocalDate.parse(orderCreateDTO.getCheckInDate()), LocalDate.parse(orderCreateDTO.getCheckOutDate()), generateOrderNumber());
    }

    private OrderDetailsDTO convertOrderToOrderDetailsDTO(Order order) {
        return new OrderDetailsDTO(order.getRoomNumber(), order.getUsername(), order.getCustomerName(), order.getCustomerEmail(), order.getCheckInDate(), order.getCheckOutDate(), order.getStatus(), order.getOrderNumber(), order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    private OrderListDTO convertOrderToOrderListDTO(Order order) {
        return new OrderListDTO(order.getRoomNumber(), order.getCustomerName(), order.getStatus(), order.getOrderNumber(), order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    private boolean checkOrderAvailable(String roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        return orderRepository.findAll((Specification<Order>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("roomNumber"), roomNumber));
            predicates.add(cb.greaterThanOrEqualTo(root.get("checkInDate"), checkInDate));
            predicates.add(cb.lessThanOrEqualTo(root.get("checkOutDate"), checkOutDate));
            predicates.add(cb.in(root.get("status")).value(Status.CREATED).value(Status.CHECKED));
            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(this::convertOrderToOrderDetailsDTO).toList().isEmpty();
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
