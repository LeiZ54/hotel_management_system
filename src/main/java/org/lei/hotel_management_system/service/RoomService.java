package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.RoomDetailsDTO;
import org.lei.hotel_management_system.DTO.RoomTypeListDTO;
import org.lei.hotel_management_system.entity.Room;
import org.lei.hotel_management_system.entity.RoomTypeInfo;
import org.lei.hotel_management_system.enums.Type;

import java.util.List;

public interface RoomService {
    RoomDetailsDTO getRoomDetailsByRoomNumber(String roomNumber);

    Room getByRoomNumber(String roomNumber);

    void setAvailable(String roomNumber, boolean available);

    //全部房间信息
    List<RoomDetailsDTO> list(String roomNumber, Type type, Boolean available, String checkInDate, String checkOutDate, Integer page);

    List<RoomTypeListDTO> typeList();

    void addRoom(Room room);

    void addRoomTypeInfo(RoomTypeInfo info);
}
