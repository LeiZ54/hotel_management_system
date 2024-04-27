package org.lei.hotel_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lei.hotel_management_system.enums.Status;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String roomNumber;

    @Column()
    private String username;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Order(String roomNumber, String username, String customerName, String customerEmail, LocalDate checkInDate, LocalDate checkOutDate, String orderNumber) {
        this.roomNumber = roomNumber;
        this.username = username;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = Status.CREATED;
        this.orderNumber = orderNumber;
        this.createdAt = LocalDateTime.now();
    }
}
