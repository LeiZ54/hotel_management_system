package org.lei.hotel_management_system.repository;

import org.lei.hotel_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
