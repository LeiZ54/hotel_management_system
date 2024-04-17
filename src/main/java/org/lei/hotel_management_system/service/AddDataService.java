package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.entity.Orders;
import org.lei.hotel_management_system.entity.Rooms;
import org.lei.hotel_management_system.entity.Users;
import org.lei.hotel_management_system.enums.Role;
import org.lei.hotel_management_system.enums.Status;
import org.lei.hotel_management_system.enums.Type;
import org.lei.hotel_management_system.repository.OrdersRepository;
import org.lei.hotel_management_system.repository.RoomRepository;
import org.lei.hotel_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AddDataService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public void add() {
        userRepository.save(new Users("LeiZ", "12345", "lei zhu", "piggy@123.com", Role.ADMIN));
        userRepository.save(new Users("fatty", "12345", "Yuheng Xia", "fatty@123.com", Role.ADMIN));
        userRepository.save(new Users("Song", "12345", "Zhiyao", "zhiyao@123.com", Role.STAFF));
        userRepository.save(new Users("Big", "12345", "Boyuan", "boyuan@123.com", Role.STAFF));
        userRepository.save(new Users("hahaha", "12345", "kaixin", "happy@123.com", Role.STAFF));

        roomRepository.save(new Rooms("1001", Type.SINGLE));
        roomRepository.save(new Rooms("1002", Type.SINGLE));
        roomRepository.save(new Rooms("1003", Type.DOUBLE));
        roomRepository.save(new Rooms("2001", Type.DOUBLE));
        roomRepository.save(new Rooms("2002", Type.SUITE));

        ordersRepository.save(new Orders("1001", "Bob", "bob@123.com", LocalDate.of(2024, 4, 19), LocalDate.of(2024, 4, 20), Status.CREATED));
        ordersRepository.save(new Orders("1002", "Jack", "jack@123.com", LocalDate.of(2024, 4, 15), LocalDate.of(2024, 4, 20), Status.CREATED));
        ordersRepository.save(new Orders("1003", "Jay", "jay@123.com", LocalDate.of(2024, 4, 20), LocalDate.of(2024, 4, 21), Status.CREATED));
        ordersRepository.save(new Orders("1001", "Run", "run@123.com", LocalDate.of(2024, 4, 16), LocalDate.of(2024, 4, 17), Status.CHECKEDIN));
        ordersRepository.save(new Orders("2001", "Pan", "pan@123.com", LocalDate.of(2024, 4, 21), LocalDate.of(2024, 4, 24), Status.CANCELED));
    }
}
