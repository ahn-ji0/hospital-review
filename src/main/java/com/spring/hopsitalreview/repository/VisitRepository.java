package com.spring.hopsitalreview.repository;

import com.spring.hopsitalreview.domain.entity.Visits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visits,Long> {
}
