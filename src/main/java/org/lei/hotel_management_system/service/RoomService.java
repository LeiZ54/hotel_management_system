package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.RoomDetailsDTO;
import org.lei.hotel_management_system.entity.Room;

import java.util.List;

public interface RoomService {
    RoomDetailsDTO getByRoomNumber(String roomNumber);

    //全部房间信息
    List<RoomDetailsDTO> findAllRooms();

    Room addRoom(Room room);
}
