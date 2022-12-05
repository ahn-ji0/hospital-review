package com.spring.hopsitalreview.controller;

import com.spring.hopsitalreview.domain.dto.ReviewCreateRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @PostMapping
    public String write(@RequestBody ReviewCreateRequest reviewCreateRequest){
        return "리뷰 등록에 성공";
    }
}
