package org.lei.hotel_management_system.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {

    @NotBlank(message = "Room number cannot be empty")
    @Pattern(regexp = "\\d+")
    private String roomNumber;

    @NotBlank(message = "Customer name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Customer name can only contain letters and spaces")
    private String customerName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be a valid email address")
    private String customerEmail;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String checkInDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String checkOutDate;
}
