package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.RoomDetailsDTO;
import org.lei.hotel_management_system.entity.Room;
import org.lei.hotel_management_system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public RoomDetailsDTO getByRoomNumber(String roomNumber) {
        return convertRoomToRoomDetailsDTO(roomRepository.getByRoomNumber(roomNumber));
    }

    @Override
    public List<RoomDetailsDTO> findAllRooms() {
        return roomRepository.findAll().stream().map(this::convertRoomToRoomDetailsDTO).toList();
    }

    private RoomDetailsDTO convertRoomToRoomDetailsDTO(Room room) {
        RoomDetailsDTO roomDetailsDTO = new RoomDetailsDTO();
        roomDetailsDTO.setRoomNumber(room.getRoomNumber());
        roomDetailsDTO.setAvailable(room.getAvailable());
        roomDetailsDTO.setType(room.getType());
        return roomDetailsDTO;
    }

}
