package com.spring.hopsitalreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HospitalResponse {
    private Long id;
    private String hospitalName;
    private String roadNameAddress;
}
