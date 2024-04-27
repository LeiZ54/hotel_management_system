package org.lei.hotel_management_system.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordUpdateDTO {
    private String oldPassword;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must be alphanumeric")
    private String newPassword;
}
