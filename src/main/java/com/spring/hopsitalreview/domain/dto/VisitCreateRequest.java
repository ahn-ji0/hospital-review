package com.spring.hopsitalreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VisitCreateRequest {
    private String hospitalName;
    private String diseaseName;
    private float cost;
}
