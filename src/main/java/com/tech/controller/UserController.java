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
@RestController //Here @Controller we can not use. If we use, then it will search for view.
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/user", "/"})
    public UserDetails getUser(){
        log.info("Getting user details...!");
        return userService.getUserDetails();
    }

    //ResponseEntity represents the whole HTTP response: status code, headers, and body.
    //Because of it, we can use it to fully configure the HTTP response.
    //ResponseEntity is a generic type. As a result, we can use any type as the response body:
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserDataById(@PathVariable String id){
        log.info("Getting user details by id = {}", id);
        UserDetails userDetails = userService.getUserDetails();
        userDetails.setUserId(id);
        return ResponseEntity.ok(userDetails);
    }

}
