package com.ibm.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fullstack.service.UserService;
import com.ibm.fullstack.view.UserView;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    @PreAuthorize("hasAuthority('admin')")
    public UserView getUserByName(@RequestParam("userName") String userName) {
        return userService.getUserByUserName(userName);
    }
}