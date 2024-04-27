package org.lei.hotel_management_system.repository;

import jakarta.transaction.Transactional;
import org.lei.hotel_management_system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> , JpaSpecificationExecutor<Room> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE rooms", nativeQuery = true)
    void truncateTable();

    Room getByRoomNumber(String roomNumber);

}
