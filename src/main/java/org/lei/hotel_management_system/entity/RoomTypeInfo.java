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
@Table(name = "room_type_infos")
public class RoomTypeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String images;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, unique = true)
    private Type type;

    public RoomTypeInfo(String images, Double price, Type type){
        this.images = images;
        this.price = price;
        this.type = type;
    }
}
