package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.UserLoginDTO;
import org.lei.hotel_management_system.DTO.UserRegisterDTO;

public interface AuthService {
    public String login(UserLoginDTO loginUser);

    public String logout();

    public String register(UserRegisterDTO registerUser);
}
