package com.example.jpademo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
    this.userService= userService;
    }
    @PostMapping
    public UserClassSpringBoot createUser(@RequestBody UserClassSpringBoot user) {
        return userService.createUser(user);
    }
    @GetMapping 
    public List<UserClassSpringBoot> getUsers(){
        return userService.getUsers();
    }
    
}
