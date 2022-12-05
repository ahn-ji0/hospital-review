package com.spring.hopsitalreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewCreateRequest {
    private String title;
    private String content;
}
