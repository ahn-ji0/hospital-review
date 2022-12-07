package com.spring.hopsitalreview.repository;

import com.spring.hopsitalreview.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    public Optional<Hospital> findByHospitalName(String hospitalName);
}
