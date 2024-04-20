package org.lei.hotel_management_system.DTO;

import lombok.Data;
import org.lei.hotel_management_system.enums.Role;

@Data
public class UserRoleUpdateDTO {
    private String username;
    private Role role;
}
