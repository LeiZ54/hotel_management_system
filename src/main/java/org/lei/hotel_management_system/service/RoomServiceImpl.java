package org.lei.hotel_management_system.service;

import jakarta.persistence.criteria.Predicate;
import org.lei.hotel_management_system.DTO.RoomDetailsDTO;
import org.lei.hotel_management_system.entity.Room;
import org.lei.hotel_management_system.enums.Type;
import org.lei.hotel_management_system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Room room = roomRepository.getByRoomNumber(roomNumber);
        if (room == null) throw new RuntimeException("Room not found!");
        return convertRoomToRoomDetailsDTO(room);
    }

    @Override
    public List<RoomDetailsDTO> list(String roomNumber, Type type, Boolean available) {
        return roomRepository.findAll((Specification<Room>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (roomNumber != null && !roomNumber.isEmpty()) {
                predicates.add(cb.equal(root.get("roomNumber"), roomNumber));
            }

            if (type != null) {
                predicates.add(cb.equal(root.get("type"), type));
            }

            if (available != null) {
                predicates.add(cb.equal(root.get("available"), available));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(this::convertRoomToRoomDetailsDTO).toList();
    }

    private RoomDetailsDTO convertRoomToRoomDetailsDTO(Room room) {
        RoomDetailsDTO roomDetailsDTO = new RoomDetailsDTO();
        roomDetailsDTO.setRoomNumber(room.getRoomNumber());
        roomDetailsDTO.setAvailable(room.getAvailable());
        roomDetailsDTO.setType(room.getType());
        return roomDetailsDTO;
    }

}
