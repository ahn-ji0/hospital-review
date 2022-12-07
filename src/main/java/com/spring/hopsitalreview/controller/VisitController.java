package com.spring.hopsitalreview.controller;

import com.spring.hopsitalreview.domain.dto.ReviewCreateRequest;
import com.spring.hopsitalreview.domain.dto.VisitCreateRequest;
import com.spring.hopsitalreview.domain.dto.VisitCreateResponse;
import com.spring.hopsitalreview.domain.entity.Visits;
import com.spring.hopsitalreview.response.Response;
import com.spring.hopsitalreview.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visit")
@Slf4j
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;
    @PostMapping
    public Response<VisitCreateResponse> write(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication){
        Visits visits = visitService.create(visitCreateRequest, authentication.getName());
        return Response.success(Visits.of(visits));
    }
}
