package org.lei.hotel_management_system.controller;

import jakarta.validation.Valid;
import org.lei.hotel_management_system.DTO.UserRoleUpdateDTO;
import org.lei.hotel_management_system.DTO.UserUpdateDTO;
import org.lei.hotel_management_system.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/list")
    public ResponseEntity<?> userList(@RequestParam String username, @RequestParam String email, @RequestParam String phoneNumber, @RequestParam String realName, @RequestParam List<String> roles) {
        return ResponseEntity.ok(userService.list(username, email, phoneNumber, realName, roles));
    }

    //前端应该传递token
    @GetMapping("/show")
    public ResponseEntity<?> userShow() {
        return ResponseEntity.ok(userService.getCurrentUserDetails());
    }

    @PostMapping("/update")
    public ResponseEntity<?> userUpdate(@RequestBody @Valid UserUpdateDTO updateUser) {
        userService.updateUser(updateUser);
        return ResponseEntity.ok("User updated successfully");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> userDelete(@RequestParam String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/roleUpdate")
    public ResponseEntity<?> userRoleUpdate(@RequestBody UserRoleUpdateDTO updateRoleUser) {
        userService.updateUserRole(updateRoleUser);
        return ResponseEntity.ok("User's role updated successfully");
    }
}
