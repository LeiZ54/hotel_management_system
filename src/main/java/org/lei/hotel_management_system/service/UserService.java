package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.UserDetailsDTO;
import org.lei.hotel_management_system.DTO.UserRoleUpdateDTO;
import org.lei.hotel_management_system.DTO.UserUpdateDTO;
import org.lei.hotel_management_system.entity.User;
import org.lei.hotel_management_system.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User addUser(User user);

    void deleteUser(String username);

    void updateUser(UserUpdateDTO updateUser);

    void updateUserRole(UserRoleUpdateDTO updateRoleUser);

    User getByEmail(String email);

    User getByUsernameAndPassword(String username, String password);

    UserDetailsDTO getUserDetails(String username);

    List<UserDetailsDTO> list(String username, String email, String realName, Role role);

    String getCurrentUserToken();

    Role getCurrentUserRole();

}
