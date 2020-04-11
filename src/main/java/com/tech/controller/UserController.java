package com.tech.controller;

import com.tech.model.UserDetails;
import com.tech.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/user", "/"})
    public UserDetails getUser(){
        log.info("Getting user details...!");
        return userService.getUserDetails();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserDataById(@PathVariable String id){
        log.info("Getting user details by id = {}", id);
        UserDetails userDetails = userService.getUserDetails();
        userDetails.setUserId(id);
        return ResponseEntity.ok(userDetails);
    }

}
