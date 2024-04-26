package org.lei.hotel_management_system.DTO;

import lombok.Data;
import org.lei.hotel_management_system.enums.Role;

@Data
public class UserDetailsDTO {
    private String username;
    private String email;
    private String phoneNumber;
    private String realName;
    private Role role;
}
