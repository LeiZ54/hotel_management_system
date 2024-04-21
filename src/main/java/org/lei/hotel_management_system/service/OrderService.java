package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.OrderCreateDTO;
import org.lei.hotel_management_system.DTO.OrderDetailsDTO;
import org.lei.hotel_management_system.DTO.OrderUpdateDTO;
import org.lei.hotel_management_system.entity.Order;
import org.lei.hotel_management_system.enums.Status;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);

    void updateOrder(OrderUpdateDTO order);

    void checkin(String orderNumber);

    void checkout(String orderNumber);

    void cancel(String orderNumber);

    List<OrderDetailsDTO> findAllOrders();

    List<OrderDetailsDTO> list(String orderNumber, String customerName, String customerEmail, Status status);

    Order convertOrderDTOToOrder(OrderCreateDTO orderCreateDTO);

    OrderDetailsDTO convertOrderToOrderDetailsDTO(Order order);

}
