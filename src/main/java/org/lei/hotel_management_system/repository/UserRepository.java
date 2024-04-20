package org.lei.hotel_management_system.repository;

import jakarta.transaction.Transactional;
import org.lei.hotel_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE users", nativeQuery = true)
    void truncateTable();
    User findByUsername(String username);
    User findByEmail(String email);
//    @Query(value = "update users c set c.password = ?2, c.realName = ?3, c.email = 4? where c.username = ?1", nativeQuery = true)
//    int updateUser(String username, String password, String realName, String email);
}

