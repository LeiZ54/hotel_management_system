package org.lei.hotel_management_system.repository;

import org.lei.hotel_management_system.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT EXISTS(SELECT * FROM users WHERE username = ?1 OR email = ?2)", nativeQuery = true)
    Long registerExists(String username, String email);

    @Query(value = "SELECT EXISTS(SELECT * FROM users WHERE (username = ?1 OR email = ?1) AND password = ?2)", nativeQuery = true)
    Long userLogin(String account, String password);

    @Query(value = "select * from users where username = ?1", nativeQuery = true)
    Users findByUsername(String username);

//    @Query(value = "update users c set c.password = ?2, c.realName = ?3, c.email = 4? where c.username = ?1", nativeQuery = true)
//    int updateUser(String username, String password, String realName, String email);
}

