package com.spring.hopsitalreview.controller;

import com.spring.hopsitalreview.domain.dto.ReviewCreateRequest;
import com.spring.hopsitalreview.domain.dto.VisitCreateRequest;
import com.spring.hopsitalreview.domain.dto.VisitCreateResponse;
import com.spring.hopsitalreview.domain.dto.VisitResponse;
import com.spring.hopsitalreview.domain.entity.Visits;
import com.spring.hopsitalreview.response.Response;
import com.spring.hopsitalreview.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visit")
@Slf4j
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;
    @PostMapping
    public Response<VisitCreateResponse> write(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication){
        VisitCreateResponse visits = visitService.create(visitCreateRequest, authentication.getName());
        return Response.success(visits);
    }
    @GetMapping
    public Response<List<VisitResponse>> list(){
        List<VisitResponse> visitResponseList = visitService.list();
        return Response.success(visitResponseList);
    }
    @GetMapping("/users/{id}")
    public Response<List<VisitResponse>> listByUser(@PathVariable Long id){
        List<VisitResponse> visitResponseList = visitService.listByUser(id);
        return Response.success(visitResponseList);
    }

    @GetMapping("/hospitals/{id}")
    public Response<List<VisitResponse>> listByHospital(@PathVariable Long id){
        List<VisitResponse> visitResponseList = visitService.listByHospital(id);
        return Response.success(visitResponseList);
    }
}
