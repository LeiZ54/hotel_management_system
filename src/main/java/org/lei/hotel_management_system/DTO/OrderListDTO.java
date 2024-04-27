package org.lei.hotel_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lei.hotel_management_system.enums.Status;

@Data
@AllArgsConstructor
public class OrderListDTO {
    private String roomNumber;

    private String customerName;

    private Status status;

    private String orderNumber;

    private String createdAt;
}
