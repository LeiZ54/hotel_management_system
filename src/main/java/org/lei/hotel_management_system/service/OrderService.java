package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.OrderCreateDTO;
import org.lei.hotel_management_system.DTO.OrderDetailsDTO;
import org.lei.hotel_management_system.DTO.OrderListDTO;
import org.lei.hotel_management_system.DTO.OrderUpdateDTO;
import org.lei.hotel_management_system.entity.Order;
import org.lei.hotel_management_system.enums.Status;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);

    void updateOrder(OrderUpdateDTO order);

    void changeStatus(String orderNumber, Status status);

    List<OrderListDTO> list(String orderNumber, String roomNumber, String username, String customerName, String customerEmail, Status status, String checkInDate, String checkOutDate, Integer page);

    OrderDetailsDTO getOrderDetails(String orderNumber);

    Order convertOrderDTOToOrder(OrderCreateDTO orderCreateDTO);
}
