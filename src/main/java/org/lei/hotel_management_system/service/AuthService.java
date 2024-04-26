package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.TokenDTO;
import org.lei.hotel_management_system.DTO.UserLoginDTO;
import org.lei.hotel_management_system.DTO.UserRegisterDTO;

public interface AuthService {
    TokenDTO login(UserLoginDTO loginUser);

    TokenDTO register(UserRegisterDTO registerUser);
}
