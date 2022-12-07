package com.spring.hopsitalreview.controller;

import com.spring.hopsitalreview.domain.dto.HospitalResponse;
import com.spring.hopsitalreview.response.Response;
import com.spring.hopsitalreview.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/{id}")
    public Response<HospitalResponse> get(@PathVariable Long id){
        HospitalResponse hospitalResponse = hospitalService.getHospital(id);
        return Response.success(hospitalResponse);
    }
}
