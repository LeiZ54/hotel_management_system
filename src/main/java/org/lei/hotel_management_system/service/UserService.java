package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.PasswordUpdateDTO;
import org.lei.hotel_management_system.DTO.UserDetailsDTO;
import org.lei.hotel_management_system.DTO.UserRoleUpdateDTO;
import org.lei.hotel_management_system.DTO.UserUpdateDTO;
import org.lei.hotel_management_system.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User addUser(User user);

    void deleteUser(String username);

    void updateUser(UserUpdateDTO updateUser);

    void updateUserRole(UserRoleUpdateDTO updateRoleUser);

    void updatePassword(PasswordUpdateDTO updatePassword);

    User getByEmail(String email);

    UserDetailsDTO getCurrentUserDetails();

    UserDetailsDTO getUserDetailsByUsername(String username);

    User getCurrentUser();
}
