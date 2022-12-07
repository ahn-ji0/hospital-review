package com.spring.hopsitalreview.domain.dto;

import com.spring.hopsitalreview.domain.entity.Visits;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VisitResponse {
    private Long id;
    private String hospitalName;
    private String userName;
    private String diseaseName;
    private float cost;

    public static VisitResponse of(Visits visits) {
        return new VisitResponse(visits.getId(),
                visits.getHospital().getHospitalName(),
                visits.getUser().getUserName(),
                visits.getDisease().getDiseaseName(),
                visits.getCost());
    }
}
