package org.lei.hotel_management_system.repository;

import org.lei.hotel_management_system.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
