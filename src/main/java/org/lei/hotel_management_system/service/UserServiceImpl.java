package org.lei.hotel_management_system.service;

import jakarta.servlet.http.HttpServletRequest;
import org.lei.hotel_management_system.DTO.PasswordUpdateDTO;
import org.lei.hotel_management_system.DTO.UserDetailsDTO;
import org.lei.hotel_management_system.DTO.UserRoleUpdateDTO;
import org.lei.hotel_management_system.DTO.UserUpdateDTO;
import org.lei.hotel_management_system.entity.User;
import org.lei.hotel_management_system.enums.Role;
import org.lei.hotel_management_system.repository.UserRepository;
import org.lei.hotel_management_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            throw new RuntimeException("Username already exists!");
        if (getByEmail(user.getEmail()) != null) throw new RuntimeException("Email already exists!");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        if (!getCurrentUser().getRole().equals(Role.ADMIN))
            throw new RuntimeException("You do not have permission to delete this user!");
        User user = userRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found!");
        if (username.equals(getCurrentUser().getUsername()))
            throw new RuntimeException("You cannot delete yourself!");
        userRepository.delete(user);
    }

    @Override
    public void updateUser(UserUpdateDTO updateUser) {
        User currentUser = getCurrentUser();
        if (getByEmail(updateUser.getEmail()) != null && !updateUser.getEmail().equals(currentUser.getEmail()))
            throw new RuntimeException("Email already exists!");
        if (updateUser.getEmail() != null) currentUser.setEmail(updateUser.getEmail());
        if (updateUser.getPhoneNumber() != null) currentUser.setPhoneNumber(updateUser.getPhoneNumber());
        if (updateUser.getRealName() != null) currentUser.setRealName(updateUser.getRealName());
        userRepository.save(currentUser);
    }

    @Override
    public void updateUserRole(UserRoleUpdateDTO updateRoleUser) {
        if (!getCurrentUser().getRole().equals(Role.ADMIN))
            throw new RuntimeException("You do not have permission to update this user's role!");
        User user = userRepository.findByUsername(updateRoleUser.getUsername());
        user.setRole(updateRoleUser.getRole());
        userRepository.save(user);
    }

    @Override
    public void updatePassword(PasswordUpdateDTO updatePassword) {
        User currentUser = getCurrentUser();
        if (!passwordEncoder.matches(updatePassword.getOldPassword(), currentUser.getPassword()))
            throw new RuntimeException("Password does not match!");
        currentUser.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
        userRepository.save(currentUser);
    }

    @Override
    public UserDetailsDTO getCurrentUserDetails() {
        return convertUserToUserDetailsDTO(getCurrentUser());
    }

    @Override
    public UserDetailsDTO getUserDetailsByUsername(String username) {
        if (getCurrentUser().getRole().equals(Role.CUSTOMER))
            throw new RuntimeException("You do not have permission to load this user!");
        return convertUserToUserDetailsDTO(userRepository.findByUsername(username));
    }

    @Override
    public User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader("Authorization");
        try {
            return userRepository.findByUsername(jwtUtil.getUsernameFromToken(token));
        } catch (Exception e) {
            throw new RuntimeException("Invalid token!");
        }
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Used to find user by username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }

    private UserDetailsDTO convertUserToUserDetailsDTO(User user) {
        UserDetailsDTO userListDTO = new UserDetailsDTO();
        userListDTO.setUsername(user.getUsername());
        userListDTO.setEmail(user.getEmail());
        userListDTO.setPhoneNumber(user.getPhoneNumber());
        userListDTO.setRealName(user.getRealName());
        userListDTO.setRole(user.getRole());
        return userListDTO;
    }
}
