package org.lei.hotel_management_system.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.lei.hotel_management_system.enums.Status;

@Data
public class OrderUpdateDTO {
    @NotBlank(message = "Order number cannot be empty!")
    private String orderNumber;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Customer name can only contain letters and spaces")
    private String customerName;

    @Email(message = "Email must be a valid email address")
    private String customerEmail;
}
