package org.lei.hotel_management_system.repository;

import org.lei.hotel_management_system.entity.Rooms;
import org.lei.hotel_management_system.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Rooms, Integer> {

    @Query(value = "select * from rooms where room_number = ?1", nativeQuery = true)
    Rooms findByRoomNumber(String roomNumber);
}
