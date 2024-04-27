package org.lei.hotel_management_system.controller;

import jakarta.annotation.Nullable;
import org.lei.hotel_management_system.DTO.OkDTO;
import org.lei.hotel_management_system.DTO.OrderCreateDTO;
import org.lei.hotel_management_system.DTO.OrderUpdateDTO;
import org.lei.hotel_management_system.entity.Order;
import org.lei.hotel_management_system.enums.Status;
import org.lei.hotel_management_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> orderCreate(@RequestBody OrderCreateDTO createOrder) {
        Order order = orderService.addOrder(orderService.convertOrderDTOToOrder(createOrder));
        return ResponseEntity.ok(new OkDTO("Order:" + order.getOrderNumber() + " has been created successfully!"));
    }

    @PostMapping("/update")
    public ResponseEntity<?> orderUpdate(@RequestBody OrderUpdateDTO updateOrder) {
        orderService.updateOrder(updateOrder);
        return ResponseEntity.ok(new OkDTO("Order:" + updateOrder.getOrderNumber() + " has been updated successfully!"));
    }

    @PostMapping("/checkin")
    public ResponseEntity<?> checkin(@RequestParam String orderNumber) {
        orderService.changeStatus(orderNumber, Status.CHECKED);
        return ResponseEntity.ok(new OkDTO("Order:" + orderNumber + " has been checked in successfully!"));
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestParam String orderNumber) {
        orderService.changeStatus(orderNumber, Status.FINISHED);
        return ResponseEntity.ok(new OkDTO("Order:" + orderNumber + " has been checked out successfully!"));
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestParam String orderNumber) {
        orderService.changeStatus(orderNumber, Status.CANCELED);
        return ResponseEntity.ok(new OkDTO("Order:" + orderNumber + " has been canceled successfully!"));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listOrders(@RequestParam String orderNumber,
                                        @RequestParam String roomNumber,
                                        @RequestParam String username,
                                        @RequestParam String customerName,
                                        @RequestParam String customerEmail,
                                        @RequestParam @Nullable Status status,
                                        @RequestParam String checkInDate,
                                        @RequestParam String checkOutDate,
                                        @RequestParam Integer page) {
        return ResponseEntity.ok(orderService.list(orderNumber, roomNumber, username, customerName, customerEmail, status, checkInDate, checkOutDate, page));
    }

    @GetMapping("/show")
    public ResponseEntity<?> show(@RequestParam String orderNumber) {
        return ResponseEntity.ok(orderService.getOrderDetails(orderNumber));
    }
}
