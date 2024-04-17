package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.entity.Rooms;
import org.lei.hotel_management_system.entity.Users;
import org.lei.hotel_management_system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    //房间信息
    public Rooms findByRoomNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }
    //全部房间信息
    public List<Rooms> getAllRooms() {
        return roomRepository.findAll();
    }



}
