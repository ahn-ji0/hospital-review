package com.spring.hopsitalreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VisitCreateRequest {
    private Long hospitalId;
    private String diseaseName;
    private float cost;
}
