package org.lei.hotel_management_system.DTO;

import lombok.Data;
import org.lei.hotel_management_system.enums.Type;

@Data
public class RoomDetailsDTO {
    private String roomNumber;

    private Type type;

    private Boolean available;
}
