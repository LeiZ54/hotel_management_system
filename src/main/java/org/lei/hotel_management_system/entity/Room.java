package org.lei.hotel_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lei.hotel_management_system.enums.Type;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private Boolean available;

    public Room(String roomNumber, Type type){
        this.roomNumber = roomNumber;
        this.type = type;
        this.available = true;
    }
}
