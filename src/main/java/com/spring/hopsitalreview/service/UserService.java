package com.spring.hopsitalreview.service;

import com.spring.hopsitalreview.domain.User;
import com.spring.hopsitalreview.domain.dto.UserDto;
import com.spring.hopsitalreview.domain.dto.UserJoinRequest;
import com.spring.hopsitalreview.domain.dto.UserJoinResponse;
import com.spring.hopsitalreview.domain.dto.UserLoginRequest;
import com.spring.hopsitalreview.exception.ErrorCode;
import com.spring.hopsitalreview.exception.HospitalReviewException;
import com.spring.hopsitalreview.repository.UserRepository;
import com.spring.hopsitalreview.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60; //1시간

    public UserDto join(UserJoinRequest userJoinRequest){
        //회원 userName 중복 체크
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewException(ErrorCode.DUPLICATE_USER_NAME,"유저 네임이 중복됩니다.");
                });
        User savedUser = userRepository.save(userJoinRequest.toEntity(encoder.encode(userJoinRequest.getPassword())));
        UserDto userDto = User.of(savedUser);
        return userDto;
    }

    public String login(UserLoginRequest userLoginRequest) {
        //User Name 있는 지 확인 -> 없으면 NOT_FOUND 예외처리
        User user = userRepository.findByUserName(userLoginRequest.getUserName())
                .orElseThrow(()-> new HospitalReviewException(ErrorCode.NOT_FOUND,"존재하지 않는 유저입니다."));

        //password가 일치하는 지 확인 -> 일치하지 않으면 INVALID_PASSWORD 예외처리
        if(!encoder.matches(userLoginRequest.getPassword(),user.getPassword())){
            throw new HospitalReviewException(ErrorCode.INVALID_PASSWORD,"비밀번호가 일치하지 않습니다.");
        }

        return JwtTokenUtils.createToken(userLoginRequest.getUserName(), secretKey, expireTimeMs);
    }
    public User getUserByUserName(String userName){
        return userRepository.findByUserName(userName)
                .orElseThrow(()->new HospitalReviewException(ErrorCode.NOT_FOUND,"존재하지 않는 유저입니다."));
    }
}
