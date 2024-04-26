package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.TokenDTO;
import org.lei.hotel_management_system.DTO.UserLoginDTO;
import org.lei.hotel_management_system.DTO.UserRegisterDTO;
import org.lei.hotel_management_system.entity.User;
import org.lei.hotel_management_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public TokenDTO login(UserLoginDTO loginUser) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
            return jwtUtil.createTokenJson(loginUser.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Username or password is incorrect!");
        }
    }

    @Override
    public TokenDTO register(UserRegisterDTO registerUser) {
        User user = userService.addUser(new User(registerUser.getUsername(), registerUser.getPassword(), registerUser.getRealName(), registerUser.getEmail(), registerUser.getPhoneNumber()));
        return jwtUtil.createTokenJson(user.getUsername());
    }
}
