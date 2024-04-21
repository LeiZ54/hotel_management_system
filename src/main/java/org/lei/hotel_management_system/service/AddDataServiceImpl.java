package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.OrderCreateDTO;
import org.lei.hotel_management_system.entity.Room;
import org.lei.hotel_management_system.entity.User;
import org.lei.hotel_management_system.enums.Role;
import org.lei.hotel_management_system.enums.Type;
import org.lei.hotel_management_system.repository.OrderRepository;
import org.lei.hotel_management_system.repository.RoomRepository;
import org.lei.hotel_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AddDataServiceImpl implements AddDataService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private OrderRepository ordersRepository;

    public void add() {
        userRepository.truncateTable();
        roomRepository.truncateTable();
        ordersRepository.truncateTable();

        User adminUser = new User("LeiZ", "12345", "lei zhu", "piggy@123.com");
        adminUser.setRole(Role.ADMIN);
        userService.addUser(adminUser);
        userService.addUser(new User("fatty", "12345", "Yuheng Xia", "fatty@123.com"));
        userService.addUser(new User("Song", "12345", "Zhiyao", "zhiyao@123.com"));
        userService.addUser(new User("Big", "12345", "Boyuan", "boyuan@123.com"));
        userService.addUser(new User("hahaha", "12345", "kaixin", "happy@123.com"));

        roomService.addRoom(new Room("1001", Type.SINGLE));
        roomService.addRoom(new Room("1002", Type.SINGLE));
        roomService.addRoom(new Room("1003", Type.DOUBLE));
        roomService.addRoom(new Room("2001", Type.DOUBLE));
        roomService.addRoom(new Room("2002", Type.SUITE));

        orderService.addOrder(orderService.convertOrderDTOToOrder(new OrderCreateDTO("1001", "Bob", "bob@123.com", "2024-04-23", "2024-04-26")));
        orderService.addOrder(orderService.convertOrderDTOToOrder(new OrderCreateDTO("1002", "Jack", "jack@123.com", "2024-04-26", "2024-04-29")));
        orderService.addOrder(orderService.convertOrderDTOToOrder(new OrderCreateDTO("1003", "Jay", "jay@123.com", "2024-04-27", "2024-04-28")));
        orderService.addOrder(orderService.convertOrderDTOToOrder(new OrderCreateDTO("1001", "Run", "run@123.com", "2024-04-19", "2024-04-20")));
        orderService.addOrder(orderService.convertOrderDTOToOrder(new OrderCreateDTO("2001", "Pan", "pan@123.com", "2024-04-11", "2024-04-22")));
    }
}
