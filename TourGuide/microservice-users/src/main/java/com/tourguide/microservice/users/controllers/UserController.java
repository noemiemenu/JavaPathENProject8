package com.tourguide.microservice.users.controllers;

import com.tourguide.library.user.User;
import com.tourguide.microservice.users.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userName}")
    private User getUser(@PathVariable String userName) {
        return userService.getUser(userName);
    }
}
