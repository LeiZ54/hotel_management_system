package org.lei.hotel_management_system.controller;

import org.lei.hotel_management_system.DTO.OrderCreateDTO;
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
        try {
            Order order = orderService.addOrder(orderService.convertOrderDTOToOrder(createOrder));
            return ResponseEntity.ok("Order:" + order.getOrderNumber() + " successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/checkin")
    public ResponseEntity<?> checkin(@RequestParam String orderNumber) {
        try {
            orderService.checkin(orderNumber);
            return ResponseEntity.ok("Order:" + orderNumber + " has been checkin successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestParam String orderNumber) {
        try {
            orderService.checkout(orderNumber);
            return ResponseEntity.ok("Order:" + orderNumber + " has been checkout successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestParam String orderNumber) {
        try {
            orderService.cancel(orderNumber);
            return ResponseEntity.ok("Order:" + orderNumber + " has been canceled successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/list")
//    public ResponseEntity<?> listOrders() {
//        try {
//            return ResponseEntity.ok(orderService.findAllOrders());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @GetMapping("/list")
    public ResponseEntity<?> listOrders(@RequestParam String orderNumber, @RequestParam String customerName, @RequestParam String customerEmail, @RequestParam Status status) {
        try {
            return ResponseEntity.ok(orderService.list(orderNumber, customerName, customerEmail, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
