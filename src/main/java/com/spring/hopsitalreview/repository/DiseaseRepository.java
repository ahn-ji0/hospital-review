package com.spring.hopsitalreview.repository;

import com.spring.hopsitalreview.domain.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<Disease,Long> {
    public Optional<Disease> findByDiseaseName(String diseaseName);
}
