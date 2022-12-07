package com.spring.hopsitalreview.domain.dto;

import com.spring.hopsitalreview.domain.entity.Disease;
import com.spring.hopsitalreview.domain.entity.Hospital;
import com.spring.hopsitalreview.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitCreateResponse {
    private Long id;
    private String hospitalName;
    private String userName;
    private String diseaseName;
    private float cost;
}
