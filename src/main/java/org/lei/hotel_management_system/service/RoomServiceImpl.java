package org.lei.hotel_management_system.service;

import jakarta.persistence.criteria.Predicate;
import org.lei.hotel_management_system.DTO.RoomDetailsDTO;
import org.lei.hotel_management_system.entity.Room;
import org.lei.hotel_management_system.entity.RoomTypeInfo;
import org.lei.hotel_management_system.enums.Type;
import org.lei.hotel_management_system.repository.RoomRepository;
import org.lei.hotel_management_system.repository.RoomTypeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeInfoRepository roomTypeRepository;

    @Override
    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void addRoomTypeInfo(RoomTypeInfo info) {
        roomTypeRepository.save(info);
    }


    @Override
    public RoomDetailsDTO getRoomDetailsByRoomNumber(String roomNumber) {
        Room room = roomRepository.getByRoomNumber(roomNumber);
        if (room == null) throw new RuntimeException("Room not found!");
        return convertRoomToRoomDetailsDTO(room);
    }

    @Override
    public Room getByRoomNumber(String roomNumber) {
        return roomRepository.getByRoomNumber(roomNumber);
    }

    @Override
    public void setAvailable(String roomNumber, boolean available) {
        Room room = roomRepository.getByRoomNumber(roomNumber);
        room.setAvailable(available);
        roomRepository.save(room);
    }

    @Override
    public List<RoomDetailsDTO> list(String roomNumber, Type type, Boolean available, Integer page) {
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
        }, PageRequest.of(page, 10)).stream().map(this::convertRoomToRoomDetailsDTO).toList();
    }

    private RoomDetailsDTO convertRoomToRoomDetailsDTO(Room room) {
        RoomDetailsDTO roomDetailsDTO = new RoomDetailsDTO();
        roomDetailsDTO.setRoomNumber(room.getRoomNumber());
        roomDetailsDTO.setPrice(roomTypeRepository.findByType(room.getType()).getPrice());
        roomDetailsDTO.setImages(roomTypeRepository.findByType(room.getType()).getImages());
        roomDetailsDTO.setAvailable(room.getAvailable());
        roomDetailsDTO.setType(room.getType());
        return roomDetailsDTO;
    }

}
