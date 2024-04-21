package org.lei.hotel_management_system.repository;

import jakarta.transaction.Transactional;
import org.lei.hotel_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE users", nativeQuery = true)
    void truncateTable();
    User findByUsername(String username);
    User findByEmail(String email);
}

