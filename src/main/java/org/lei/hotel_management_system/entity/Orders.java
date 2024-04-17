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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String roomNumber;

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

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Orders(String roomNumber, String customerName, String customerEmail, LocalDate checkInDate, LocalDate checkOutDate, Status status) {
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }
}
