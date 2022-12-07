package com.spring.hopsitalreview.service;

import com.spring.hopsitalreview.domain.dto.VisitCreateRequest;
import com.spring.hopsitalreview.domain.dto.VisitCreateResponse;
import com.spring.hopsitalreview.domain.dto.VisitResponse;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VisitService {
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final DiseaseRepository diseaseRepository;

    public VisitCreateResponse create(VisitCreateRequest visitCreateRequest, String userName){
        //유저가 있는지 확인
        User user = userRepository.findByUserName(userName).orElseThrow(()->
                new HospitalReviewException(ErrorCode.NOT_FOUND,"존재하지 않는 유저입니다."));

        //병원이 있는 지 확인
        Hospital hospital = hospitalRepository.findById(visitCreateRequest.getHospitalId())
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

        return Visits.of(savedVisits);
    }

    public List<VisitResponse> list() {
        List<Visits> visitsList = visitRepository.findAll();
        List<VisitResponse> visitResponseList = visitsList.stream()
                .map(visits -> VisitResponse.of(visits))
                .collect(Collectors.toList());
        return visitResponseList;
    }

    public List<VisitResponse> listByUser(Long id) {
        User savedUser = userRepository.findById(id).orElseThrow(()->new HospitalReviewException(ErrorCode.NOT_FOUND, "해당 유저가 없습니다."));
        List<Visits> visitsList = visitRepository.findByUser(savedUser);
        List<VisitResponse> visitResponseList = visitsList.stream()
                .map(visits -> VisitResponse.of(visits))
                .collect(Collectors.toList());
        return visitResponseList;
    }

    public List<VisitResponse> listByHospital(Long id) {
        Hospital savedHospital = hospitalRepository.findById(id).orElseThrow(()->new HospitalReviewException(ErrorCode.NOT_FOUND, "해당 병원이 없습니다."));
        List<Visits> visitsList = visitRepository.findByHospital(savedHospital);
        List<VisitResponse> visitResponseList = visitsList.stream()
                .map(visits -> VisitResponse.of(visits))
                .collect(Collectors.toList());
        return visitResponseList;
    }
}
