package org.lei.hotel_management_system.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 10, message = "Username must be between 3 and 10 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must be alphanumeric")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "Real name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Real name can only contain letters and spaces")
    private String realName;
}
