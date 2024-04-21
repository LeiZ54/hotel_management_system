package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.UserLoginDTO;
import org.lei.hotel_management_system.DTO.UserRegisterDTO;

public interface AuthService {
    String login(UserLoginDTO loginUser);

    String logout();

    String register(UserRegisterDTO registerUser);
}
