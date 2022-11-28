package com.spring.hopsitalreview.controller;

import com.spring.hopsitalreview.domain.dto.UserDto;
import com.spring.hopsitalreview.domain.dto.UserJoinRequest;
import com.spring.hopsitalreview.domain.dto.UserJoinResponse;
import com.spring.hopsitalreview.response.Response;
import com.spring.hopsitalreview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest){
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(),userDto.getEmail()));
    }
}
