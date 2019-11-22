package com.ibm.fullstack.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    DiscoveryClient discoveryClient;
    
    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public String register(@RequestBody User  user) {
    	if(!userService.existsUserName(user.getUserName())) {
    		return userService.register(user) == null ? "{\"result\": \"failed\"}" : "{\"result\": \"success\"}";
    	}else {
    		return "{\"result\": \"exists\"}";
    	}
    }
    
    @RequestMapping("/hello")
    public List<String> getServices() {
        List<String> instance = discoveryClient.getServices();
        //打印服务的服务id
        logger.info("*********" + instance.toString());
        return instance;
    }
}