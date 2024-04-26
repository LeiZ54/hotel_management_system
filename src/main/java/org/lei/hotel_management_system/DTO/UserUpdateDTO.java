package org.lei.hotel_management_system.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserUpdateDTO {
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
