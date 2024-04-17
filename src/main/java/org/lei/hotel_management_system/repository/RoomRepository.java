package org.lei.hotel_management_system.repository;

import org.lei.hotel_management_system.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Rooms, Integer> {
}
