package com.spring.hopsitalreview.exception;

import com.spring.hopsitalreview.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runTimeExceptionHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error("INTERNAL SERVER ERROR"));
    }

    @ExceptionHandler(HospitalReviewException.class)
    public ResponseEntity<?> hospitalReviewAppExceptionHandler(HospitalReviewException e){
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }
}