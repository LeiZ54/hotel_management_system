package org.lei.hotel_management_system.controller;

import org.apache.catalina.User;
import org.lei.hotel_management_system.entity.Users;
import org.lei.hotel_management_system.enums.Role;
import org.lei.hotel_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //按理前端应该传递一个token 来验证是否为管理员
    //token 解析和接受全部交给你来改写
    @GetMapping("/list")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public boolean RegisterUser() {
        Users user = new Users();
        //目前写死，但是之后要改成前端传输
        user.setUsername("123");
        user.setPassword("piggy@1");
        user.setRealName("牛大春");
        user.setRole(Role.STAFF);
        user.setEmail("dawd@gmail.com");
        /////
        return userService.addUser(user);
    }

    @PostMapping("/login")
    //应该return tokean ，目前先return boolean
    public boolean usersLogin() {
        //目前写死，但是之后要改成前端传输
        String account = "piggy@123.com"; //username or email
        String password = "1234";
        ///////
        return userService.userLogin(account, password);
    }


    //前端应该传递token
    @GetMapping("/show")
    public ResponseEntity<Users> userShow(){
        //按理我需要解析token， 现在交给你解析
        //解析完tokean，应该有个username，现在我把它写死
        String username = "LeiZ";
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    //按理前端应该传递一个token 来验证人员身份
    //token 解析和接受全部交给你来改写
    //传输的数据暂时还不确定，我先写死
//    @PostMapping("/update")
//    public boolean updateUsers() {
//        //应该token解析用户名，现在写死
//        String username = "LeiZ";
//        //建议username不可修改
//        //暂时不确定前端传输的数据，先写死
//        String password = "1234";
//        String email = "dawd@gmail.com";
//        String realName = "1231";
//
//        return userService.updateUser(username, password, email, realName) == 1;
//    }


}
