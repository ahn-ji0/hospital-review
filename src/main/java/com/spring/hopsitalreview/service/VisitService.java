package com.spring.hopsitalreview.service;

import com.spring.hopsitalreview.domain.dto.VisitCreateRequest;
import com.spring.hopsitalreview.domain.entity.Disease;
import com.spring.hopsitalreview.domain.entity.Hospital;
import com.spring.hopsitalreview.domain.entity.User;
import com.spring.hopsitalreview.domain.entity.Visits;
import com.spring.hopsitalreview.exception.ErrorCode;
import com.spring.hopsitalreview.exception.HospitalReviewException;
import com.spring.hopsitalreview.repository.DiseaseRepository;
import com.spring.hopsitalreview.repository.HospitalRepository;
import com.spring.hopsitalreview.repository.UserRepository;
import com.spring.hopsitalreview.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final DiseaseRepository diseaseRepository;

    public Visits create(VisitCreateRequest visitCreateRequest, String userName){
        //유저가 있는지 확인
        User user = userRepository.findByUserName(userName).orElseThrow(()->
                new HospitalReviewException(ErrorCode.NOT_FOUND,"존재하지 않는 유저입니다."));

        //병원이 있는 지 확인
        Hospital hospital = hospitalRepository.findByHospitalName(visitCreateRequest.getHospitalName())
                .orElseThrow(()->new HospitalReviewException(ErrorCode.NOT_FOUND, "존재하지 않는 병원입니다."));

        //병명이 있는 지 확인
        Optional<Disease> optionalDisease = diseaseRepository.findByDiseaseName(visitCreateRequest.getDiseaseName());
        Disease savedDisease = null;

        if(optionalDisease.isEmpty()){
            savedDisease = diseaseRepository.save(Disease.builder().diseaseName(visitCreateRequest.getDiseaseName()).build());
        } else{
            savedDisease = optionalDisease.get();
        }

        Visits savedVisits = visitRepository.save(Visits.builder()
                .hospital(hospital)
                .user(user)
                .disease(savedDisease)
                .cost(visitCreateRequest.getCost())
                .build());

        return savedVisits;
    }
}
