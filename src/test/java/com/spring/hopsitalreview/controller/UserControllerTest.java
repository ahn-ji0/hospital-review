package com.spring.hopsitalreview.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.hopsitalreview.domain.dto.UserDto;
import com.spring.hopsitalreview.domain.dto.UserJoinRequest;
import com.spring.hopsitalreview.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Join 성공 테스트")
    void join() throws Exception {
        UserJoinRequest userJoinRequest = new UserJoinRequest("jiyoung","1234","jy@naver.com");

        given(userService.join(any())).willReturn(new UserDto(1l,userJoinRequest.getUserName(),userJoinRequest.getEmail()));

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userJoinRequest))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("Join 실패 테스트")
    void join2() throws Exception {
        UserJoinRequest userJoinRequest = new UserJoinRequest("jiyoung","1234","jy@naver.com");

        given(userService.join(any())).willReturn(new UserDto(1l,userJoinRequest.getUserName(),userJoinRequest.getEmail()));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}