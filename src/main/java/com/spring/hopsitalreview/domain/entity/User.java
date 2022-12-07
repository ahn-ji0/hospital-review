package com.spring.hopsitalreview.domain.entity;

import com.spring.hopsitalreview.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public static UserDto of(User user){
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmailAddress())
                .build();
    }
}
