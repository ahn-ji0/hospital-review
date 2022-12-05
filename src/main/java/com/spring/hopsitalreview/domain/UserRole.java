package com.spring.hopsitalreview.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    ADMIN("admin"),USER("user");

    private String role;
}
