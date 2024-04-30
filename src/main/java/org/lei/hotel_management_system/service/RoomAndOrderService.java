package org.lei.hotel_management_system.service;

import java.util.List;

public interface RoomAndOrderService {
    List<String> findUnavailableRoomNumbersByDates(String checkInDate, String checkOutDate);

}
