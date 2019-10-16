package com.ibm.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fullstack.dto.UserDtl;
import com.ibm.fullstack.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping(value = "/user")
//    @PreAuthorize("hasAuthority('admin')")
//    public UserDtl getUserByName(@RequestParam("userId") Long userId) {
//        return userService.getUserById(userId);
//    }
}