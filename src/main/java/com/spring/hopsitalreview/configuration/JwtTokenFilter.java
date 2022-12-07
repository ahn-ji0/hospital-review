package com.spring.hopsitalreview.configuration;

import com.spring.hopsitalreview.domain.entity.User;
import com.spring.hopsitalreview.service.UserService;
import com.spring.hopsitalreview.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader:{}", authorizationHeader);

        //1. 토큰이 없는 경우
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //토큰 분리
        String token;
        try {
            token = authorizationHeader.split(" ")[1];
        } catch (Exception e) {
            //2. 적절하지 않은 토큰
            log.info("토큰 추출에 실패했습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        //3. 시간이 만료된 토큰
        if (JwtTokenUtils.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userName = JwtTokenUtils.getUserName(token,secretKey);
        log.info("userName = {}",userName);

        //user detail 가져오기
        User user = userService.getUserByUserName(userName);
        log.info("userRole:{}",user.getRole());

        //위의 경우를 제외하고 허가
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), null, List.of(new SimpleGrantedAuthority(user.getRole().name())));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); //권한 부여
        filterChain.doFilter(request, response); //다음 체인으로 넘어간다.
    }
}
