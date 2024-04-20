package org.lei.hotel_management_system.controller;

import org.lei.hotel_management_system.DTO.RoomDetailsDTO;
import org.lei.hotel_management_system.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomService;

    //暂时回传room实体的全部内容，以后再增加回传内容
    @GetMapping("/show")
    public ResponseEntity<RoomDetailsDTO> show(String roomNumber) {
        return ResponseEntity.ok(roomService.getByRoomNumber(roomNumber));
    }

    //暂时回传room实体的全部内容，以后再增加回传内容
    @GetMapping("/list")
    public ResponseEntity<List<RoomDetailsDTO>> list() {
        return ResponseEntity.ok(roomService.findAllRooms());
    }

}
