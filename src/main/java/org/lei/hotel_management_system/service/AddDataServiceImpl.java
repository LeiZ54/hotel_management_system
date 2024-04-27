package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.DTO.OrderCreateDTO;
import org.lei.hotel_management_system.entity.Room;
import org.lei.hotel_management_system.entity.RoomTypeInfo;
import org.lei.hotel_management_system.entity.User;
import org.lei.hotel_management_system.enums.Role;
import org.lei.hotel_management_system.enums.Type;
import org.lei.hotel_management_system.repository.OrderRepository;
import org.lei.hotel_management_system.repository.RoomRepository;
import org.lei.hotel_management_system.repository.RoomTypeInfoRepository;
import org.lei.hotel_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

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
    @Autowired
    private RoomTypeInfoRepository roomTypeInfoRepository;

    public void add() {
        userRepository.truncateTable();
        roomTypeInfoRepository.truncateTable();
        roomRepository.truncateTable();
        ordersRepository.truncateTable();

        User adminUser = new User("LeiZ", "12345", "lei zhu", "piggy@123.com", "8575447940");
        adminUser.setRole(Role.ADMIN);
        userService.addUser(adminUser);
        User staffUser = new User("fatty", "12345", "Yuheng Xia", "fatty@123.com", "8575447941");
        staffUser.setRole(Role.STAFF);
        userService.addUser(staffUser);
        userService.addUser(new User("Song", "12345", "Zhiyao", "zhiyao@123.com", "8575447942"));
        userService.addUser(new User("Big", "12345", "Boyuan", "boyuan@123.com", "8575447943"));
        userService.addUser(new User("hahaha", "12345", "kaixin", "happy@123.com", "8575447944"));

        ArrayList<String> singleRoomImages = new ArrayList<>();
        singleRoomImages.add("https://s2.loli.net/2024/04/28/Xnkfm8olOvDcGTN.jpg");
        singleRoomImages.add("https://s2.loli.net/2024/04/28/ZGc7p48eIg1JrTi.webp");

        ArrayList<String> doubleRoomImages = new ArrayList<>();
        doubleRoomImages.add("https://s2.loli.net/2024/04/28/RbncmSt4eqsHgCr.jpg");
        doubleRoomImages.add("https://s2.loli.net/2024/04/28/RbncmSt4eqsHgCr.jpg");

        ArrayList<String> suiteRoomImages = new ArrayList<>();
        suiteRoomImages.add("https://s2.loli.net/2024/04/28/sDH67qf2pVcKmEb.jpg");
        suiteRoomImages.add("https://s2.loli.net/2024/04/28/wrWQ9RCyKqgx5uG.jpg");

        roomService.addRoomTypeInfo(new RoomTypeInfo(Arrays.toString(singleRoomImages.toArray()), 149.00, Type.SINGLE));
        roomService.addRoomTypeInfo(new RoomTypeInfo(Arrays.toString(doubleRoomImages.toArray()), 189.00, Type.DOUBLE));
        roomService.addRoomTypeInfo(new RoomTypeInfo(Arrays.toString(suiteRoomImages.toArray()), 349.00, Type.SUITE));

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
