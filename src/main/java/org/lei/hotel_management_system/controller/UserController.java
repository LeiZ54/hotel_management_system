package org.lei.hotel_management_system.controller;

import org.lei.hotel_management_system.DTO.UserRoleUpdateDTO;
import org.lei.hotel_management_system.DTO.UserUpdateDTO;
import org.lei.hotel_management_system.enums.Role;
import org.lei.hotel_management_system.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/list")
    public ResponseEntity<?> userList(@RequestParam String username, @RequestParam String email, @RequestParam String realName, @RequestParam Role role) {
        try {
            return ResponseEntity.ok(userService.list(username, email, realName, role));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //前端应该传递token
    @GetMapping("/show")
    public ResponseEntity<?> userShow(@RequestParam String username) {
        try {
            return ResponseEntity.ok(userService.getUserDetails(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> userUpdate(@RequestBody UserUpdateDTO updateUser) {
        try {
            userService.updateUser(updateUser);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<?> userDelete(@RequestParam String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/roleUpdate")
    public ResponseEntity<?> userRoleUpdate(@RequestBody UserRoleUpdateDTO updateRoleUser) {
        try {
            userService.updateUserRole(updateRoleUser);
            return ResponseEntity.ok("User's role updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
