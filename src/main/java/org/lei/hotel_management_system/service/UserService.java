package org.lei.hotel_management_system.service;

import org.lei.hotel_management_system.entity.Users;
import org.lei.hotel_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    //返回所有用户信息
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    //用户注册
    public boolean addUser(Users user) {
        if (userRepository.registerExists(user.getUsername(), user.getEmail()) <= 0){
            userRepository.save(user);
            return true;
        }
        return false;
    }

    //用户登录
    //暂时返回boolean
    //正常要生成token， 返回token
    public boolean userLogin(String account, String password) {
        return userRepository.userLogin(account, password) > 0;
    }
    //用户信息
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    //用户更新信息
//    public int updateUser(String username, String password, String realName, String email) {
//        return userRepository.updateUser(username, password, realName, email);
//    }

}
