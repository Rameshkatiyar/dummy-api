package com.tech.service;

import com.tech.model.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public UserDetails getUserDetails(){
        return UserDetails.builder()
                .userId("123")
                .firstName("Ramesh")
                .lastName("Katiyar")
                .accountNumbers(new int[]{123,456,789})
                .build();
    }
}
