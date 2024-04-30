package org.lei.hotel_management_system.DTO;

import lombok.Data;
import org.lei.hotel_management_system.enums.Type;

@Data
public class RoomTypeListDTO {
    private Type type;
    private String images;
    private Double price;
}
