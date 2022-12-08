package com.spring.hopsitalreview.domain.entity;

import com.spring.hopsitalreview.domain.dto.VisitCreateResponse;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Visits extends BaseEntity{
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
