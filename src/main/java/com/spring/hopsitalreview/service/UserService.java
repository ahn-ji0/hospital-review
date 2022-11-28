package com.spring.hopsitalreview.service;

import com.spring.hopsitalreview.domain.User;
import com.spring.hopsitalreview.domain.dto.UserDto;
import com.spring.hopsitalreview.domain.dto.UserJoinRequest;
import com.spring.hopsitalreview.domain.dto.UserJoinResponse;
import com.spring.hopsitalreview.exception.ErrorCode;
import com.spring.hopsitalreview.exception.HospitalReviewException;
import com.spring.hopsitalreview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDto join(UserJoinRequest userJoinRequest){
        //회원 userName 중복 체크
        //회원가입.save
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewException(ErrorCode.DUPLICATE_USER_NAME,"유저 네임이 중복됩니다.");
                });
        User savedUser = userRepository.save(userJoinRequest.toEntity());
        UserDto userDto = User.of(savedUser);
        return userDto;
    }
}
