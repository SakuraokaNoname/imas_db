//package com.db.imas.controller;
//
//import com.db.imas.model.dto.ResultDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author noname
// * @create 2021/7/10
// */
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/login")
//    @ResponseBody
//    public ResultDTO UserLogin(@RequestBody String token){
//        return userService.login();
//    }
//
//}
