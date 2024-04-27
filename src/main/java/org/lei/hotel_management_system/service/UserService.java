package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.UserDetailsDTO;
import org.lei.hotel_management_system.DTO.UserRoleUpdateDTO;
import org.lei.hotel_management_system.DTO.UserUpdateDTO;
import org.lei.hotel_management_system.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User addUser(User user);

    void deleteUser(String username);

    void updateUser(UserUpdateDTO updateUser);

    void updateUserRole(UserRoleUpdateDTO updateRoleUser);

    User getByEmail(String email);

    UserDetailsDTO getCurrentUserDetails();

    List<UserDetailsDTO> list(String username, String email, String phoneNumber, String realName, List<String> roles);

    User getCurrentUser();
}
