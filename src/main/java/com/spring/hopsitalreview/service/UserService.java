package com.spring.hopsitalreview.service;

import com.spring.hopsitalreview.domain.dto.UserDto;
import com.spring.hopsitalreview.domain.dto.UserJoinRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto join(UserJoinRequest userJoinRequest){
        return new UserDto("","","");
    }
}
