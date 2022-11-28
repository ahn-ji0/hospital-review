package com.spring.hopsitalreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserJoinResponse {
    private String userName;
    private String email;
}
