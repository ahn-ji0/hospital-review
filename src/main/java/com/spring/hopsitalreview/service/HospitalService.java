package com.spring.hopsitalreview.service;

import com.spring.hopsitalreview.domain.dto.HospitalResponse;
import com.spring.hopsitalreview.domain.entity.Hospital;
import com.spring.hopsitalreview.exception.ErrorCode;
import com.spring.hopsitalreview.exception.HospitalReviewException;
import com.spring.hopsitalreview.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalResponse getHospital(Long id){
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(()->new HospitalReviewException(ErrorCode.NOT_FOUND,"존재하지 않는 병원 아이디입니다."));
        HospitalResponse hospitalResponse = Hospital.of(hospital);

        return hospitalResponse;
    }
}
