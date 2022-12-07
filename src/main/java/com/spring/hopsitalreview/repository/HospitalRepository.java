package com.spring.hopsitalreview.repository;

import com.spring.hopsitalreview.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}
