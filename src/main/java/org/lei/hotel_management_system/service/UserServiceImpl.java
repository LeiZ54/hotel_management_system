package org.lei.hotel_management_system.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.http.HttpServletRequest;
import org.lei.hotel_management_system.DTO.UserDetailsDTO;
import org.lei.hotel_management_system.DTO.UserRoleUpdateDTO;
import org.lei.hotel_management_system.DTO.UserUpdateDTO;
import org.lei.hotel_management_system.entity.User;
import org.lei.hotel_management_system.enums.Role;
import org.lei.hotel_management_system.repository.UserRepository;
import org.lei.hotel_management_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
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

    public UserDetailsDTO getUserDetails(String username) {
        if (getCurrentUser().getRole().equals(Role.CUSTOMER) && !username.equals(getCurrentUser().getUsername())) {
            throw new RuntimeException("You cannot access this user!");
        }
        return convertUserToUserDetailsDTO((User) loadUserByUsername(username));
    }

    @Override
    public List<UserDetailsDTO> list(String username, String email, String phoneNumber, String realName, List<String> roles) {
        if (getCurrentUser().getRole().equals(Role.CUSTOMER))
            throw new RuntimeException("You cannot access user list!");
        return userRepository.findAll((Specification<User>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (username != null && !username.isEmpty()) {
                predicates.add(cb.like(root.get("username"), "%" + username + "%"));
            }

            if (email != null && !email.isEmpty()) {
                predicates.add(cb.like(root.get("email"), "%" + email + "%"));
            }

            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                predicates.add(cb.like(root.get("phoneNumber"), "%" + phoneNumber + "%"));
            }

            if (realName != null && !realName.isEmpty()) {
                predicates.add(cb.like(root.get("realName"), "%" + realName + "%"));
            }

            if (roles != null && !roles.isEmpty()) {
                CriteriaBuilder.In<String> roleIn = cb.in(root.get("role"));
                roles.forEach(roleIn::value);
                predicates.add(roleIn);
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(this::convertUserToUserDetailsDTO).toList();
    }

    @Override
    public User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader("Authorization");
        System.out.println(token);
        return userRepository.findByUsername(jwtUtil.getUsernameFromToken(token));
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
