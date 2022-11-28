package com.spring.hopsitalreview.domain;

import com.spring.hopsitalreview.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String emailAddress;

    public static UserDto of(User user){
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmailAddress())
                .build();
    }
}
