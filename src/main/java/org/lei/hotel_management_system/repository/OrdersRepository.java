package org.lei.hotel_management_system.repository;


import org.lei.hotel_management_system.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
