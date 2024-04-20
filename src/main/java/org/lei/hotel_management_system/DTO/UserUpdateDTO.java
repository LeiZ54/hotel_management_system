package org.lei.hotel_management_system.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserUpdateDTO {
    private String username;

    private String email;
    @Email(message = "Email must be a valid email address")
    private String newEmail;

    private String realName;
    @NotBlank(message = "Real name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Real name can only contain letters and spaces")
    private String newRealName;
}
