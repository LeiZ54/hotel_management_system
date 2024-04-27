package org.lei.hotel_management_system.repository;

import jakarta.transaction.Transactional;
import org.lei.hotel_management_system.entity.RoomTypeInfo;
import org.lei.hotel_management_system.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeInfoRepository extends JpaRepository<RoomTypeInfo, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE room_type_infos", nativeQuery = true)
    void truncateTable();

    RoomTypeInfo findByType(Type type);
}
