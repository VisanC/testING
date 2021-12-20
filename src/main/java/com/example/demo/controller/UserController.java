package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestParam String userName, @RequestParam String userEmail, @RequestParam String password){
        int result = userService.registerUser(userName,userEmail,password);
        ResponseEntity<String> res;

        switch(result){
            case 0:
                res=new ResponseEntity<String>("Registration successful",HttpStatus.OK);
                break;
            case 1:
                res =new  ResponseEntity<String>("User already exists",HttpStatus.INTERNAL_SERVER_ERROR);
                break;
            case 2:
                res =new  ResponseEntity<String>("Your password is too weak.Please use 8+ characters including digits",HttpStatus.INTERNAL_SERVER_ERROR);
                break;
            default:
                res =new  ResponseEntity<String>("Unknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info(res.toString());
        return res;

    }
}
