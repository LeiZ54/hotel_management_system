package org.lei.hotel_management_system.controller;

import jakarta.annotation.Nullable;
import org.lei.hotel_management_system.enums.Type;
import org.lei.hotel_management_system.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomService;

    //暂时回传room实体的全部内容，以后再增加回传内容
    @GetMapping("/show")
    public ResponseEntity<?> show(String roomNumber) {
        return ResponseEntity.ok(roomService.getRoomDetailsByRoomNumber(roomNumber));
    }

    //暂时回传room实体的全部内容，以后再增加回传内容
    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam String roomNumber, @RequestParam @Nullable Type type, @RequestParam @Nullable Boolean available, @RequestParam String checkInDate, @RequestParam String checkOutDate, @RequestParam Integer page) {
        return ResponseEntity.ok(roomService.list(roomNumber, type, available, checkInDate, checkOutDate, page));
    }

    @GetMapping("/typeList")
    public ResponseEntity<?> typeList() {
        return ResponseEntity.ok(roomService.typeList());
    }
}
