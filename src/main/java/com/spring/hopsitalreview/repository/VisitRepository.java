package com.spring.hopsitalreview.repository;

import com.spring.hopsitalreview.domain.entity.Hospital;
import com.spring.hopsitalreview.domain.entity.User;
import com.spring.hopsitalreview.domain.entity.Visits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visits,Long> {
    List<Visits> findByUser(User user);
    List<Visits> findByHospital(Hospital hospital);
}
