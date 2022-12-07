package com.spring.hopsitalreview.domain.dto;

import com.spring.hopsitalreview.domain.User;
import com.spring.hopsitalreview.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;

    public User toEntity(String password){
        return User.builder()
                .userName(this.userName)
                .password(password)
                .emailAddress(this.email)
                .role(UserRole.USER)
                .build();
    }
}
