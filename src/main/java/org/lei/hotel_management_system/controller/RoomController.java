package org.lei.hotel_management_system.controller;

import org.lei.hotel_management_system.entity.Users;
import org.lei.hotel_management_system.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.lei.hotel_management_system.entity.Rooms;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    //暂时回传room实体的全部内容，以后再增加回传内容
    @GetMapping("/show")
    public ResponseEntity<Rooms> show(String roomNumber) {
        return ResponseEntity.ok(roomService.findByRoomNumber(roomNumber));
    }

    //暂时回传room实体的全部内容，以后再增加回传内容
    @GetMapping("/list")
    public ResponseEntity<List<Rooms>> list() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }



}
