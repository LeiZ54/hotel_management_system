package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.RoomDetailsDTO;
import org.lei.hotel_management_system.entity.Room;
import org.lei.hotel_management_system.enums.Type;

import java.util.List;

public interface RoomService {
    RoomDetailsDTO getByRoomNumber(String roomNumber);

    //全部房间信息
    List<RoomDetailsDTO> list(String roomNumber, Type type, Boolean available);

    Room addRoom(Room room);
}
