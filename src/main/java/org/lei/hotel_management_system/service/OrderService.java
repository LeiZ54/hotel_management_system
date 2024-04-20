package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.OrderCreateDTO;
import org.lei.hotel_management_system.DTO.OrderUpdateDTO;
import org.lei.hotel_management_system.entity.Order;

public interface OrderService {
    Order addOrder(Order order);

    void updateOrder(OrderUpdateDTO order);

    void checkin(String orderNumber);

    void checkout(String orderNumber);

    void cancel(String orderNumber);

    Order convertOrderDTOToOrder(OrderCreateDTO orderCreateDTO);

}
