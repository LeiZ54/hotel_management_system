package org.lei.hotel_management_system.service;

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
    private UserServiceImpl userService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(UserLoginDTO loginUser) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        if (userService.loadUserByUsername(loginUser.getUsername()) == null)
            throw new RuntimeException("User not found!");
        if (userService.getByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword()) == null)
            throw new RuntimeException("Username or password is incorrect!");
        return jwtUtil.createToken(loginUser.getUsername());
    }

    @Override
    public String logout() {
        return "";
    }

    @Override
    public String register(UserRegisterDTO registerUser) {
        User user = userService.addUser(new User(registerUser.getUsername(), registerUser.getPassword(), registerUser.getRealName(), registerUser.getEmail()));
        return jwtUtil.createToken(user.getUsername());
    }

}
