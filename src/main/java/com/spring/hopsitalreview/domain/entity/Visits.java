package com.spring.hopsitalreview.domain.entity;

import com.spring.hopsitalreview.domain.dto.VisitCreateResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Visits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    private float cost;


    public static VisitCreateResponse of(Visits visits) {
        return VisitCreateResponse.builder()
                .id(visits.getId())
                .hospitalName(visits.getHospital().getHospitalName())
                .userName(visits.getUser().getUserName())
                .diseaseName(visits.getDisease().getDiseaseName())
                .cost(visits.getCost())
                .build();
    }
}
