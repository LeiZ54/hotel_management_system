package org.lei.hotel_management_system.repository;


import jakarta.transaction.Transactional;
import org.lei.hotel_management_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE orders", nativeQuery = true)
    void truncateTable();

    Order findByOrderNumber(String orderNumber);
}
