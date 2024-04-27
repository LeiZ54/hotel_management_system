package org.lei.hotel_management_system.controller;

import jakarta.validation.Valid;
import org.lei.hotel_management_system.DTO.OkDTO;
import org.lei.hotel_management_system.DTO.PasswordUpdateDTO;
import org.lei.hotel_management_system.DTO.UserRoleUpdateDTO;
import org.lei.hotel_management_system.DTO.UserUpdateDTO;
import org.lei.hotel_management_system.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserDetailsByUsername(username));
    }

    //前端应该传递token
    @GetMapping("/show")
    public ResponseEntity<?> userShow() {
        return ResponseEntity.ok(userService.getCurrentUserDetails());
    }

    @PostMapping("/update")
    public ResponseEntity<?> userUpdate(@RequestBody @Valid UserUpdateDTO updateUser) {
        userService.updateUser(updateUser);
        return ResponseEntity.ok(new OkDTO("User updated successfully!"));
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> passwordUpdate(@RequestBody @Valid PasswordUpdateDTO passwordUpdateDTO) {
        userService.updatePassword(passwordUpdateDTO);
        return ResponseEntity.ok(new OkDTO("Password updated successfully!"));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> userDelete(@RequestParam String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok(new OkDTO("User deleted successfully!"));
    }

    @PostMapping("/roleUpdate")
    public ResponseEntity<?> userRoleUpdate(@RequestBody UserRoleUpdateDTO updateRoleUser) {
        userService.updateUserRole(updateRoleUser);
        return ResponseEntity.ok(new OkDTO("User's role updated successfully!"));
    }
}
