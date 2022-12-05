package com.spring.hopsitalreview.controller;

import com.spring.hopsitalreview.domain.dto.ReviewCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@Slf4j
public class ReviewController {

    @PostMapping
    public String write(@RequestBody ReviewCreateRequest reviewCreateRequest, Authentication authentication){
        log.info("Controller user:{}", authentication.getName());
        return "리뷰 등록에 성공";
    }
}
