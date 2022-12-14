package com.spring.hopsitalreview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATE_USER_NAME(HttpStatus.CONFLICT, "USER NAME CONFLICT"),
    NOT_FOUND(HttpStatus.NOT_FOUND,"USER NOT FOUND"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,"PASSWORD INVALID");

    private HttpStatus httpStatus;
    private String message;
}
