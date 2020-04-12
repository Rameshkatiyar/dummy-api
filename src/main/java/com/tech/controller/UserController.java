package com.tech.controller;

import com.tech.model.UserDetails;
import com.tech.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
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

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody UserDetails userDetails){
        log.info("Adding user with id: {}, Name: {}", userDetails.getUserId(), userDetails.getFirstName());
        return ResponseEntity.ok("Added User Successfully.");
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable String id, @RequestBody UserDetails userDetails){
        log.info("Updating user with id: {}, Name: {}", id, userDetails.getFirstName());
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id){
        log.info("Deleting user with id: {}.", id);
    }

}
