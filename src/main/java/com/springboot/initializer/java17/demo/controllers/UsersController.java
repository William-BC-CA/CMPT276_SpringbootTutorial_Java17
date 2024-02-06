package com.springboot.initializer.java17.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.initializer.java17.demo.models.User;
import com.springboot.initializer.java17.demo.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller // Listen to the requests
public class UsersController {

    @Autowired
    private UserRepository userRepo;
    @GetMapping("/users/view") // Can call it whatever you want
    public String getAllUsers(Model model){
        System.out.println("Getting all users");
        List<User> users = userRepo.findAll();
        // end of database call
        model.addAttribute("us", users);
        return "users/showAll";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response){
        System.out.println("ADD user");
        String newName = newuser.get("name");
        String newPwd = newuser.get("password");
        int newSize = Integer.parseInt(newuser.get("size"));
        userRepo.save(new User(newName,newPwd,newSize)); // Does the insert command
        response.setStatus(201);
        return "users/addedUser";
    }
}
