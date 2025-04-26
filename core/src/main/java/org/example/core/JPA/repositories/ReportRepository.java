package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 举报信息接口
 */

public interface ReportRepository extends JpaRepository<Report, Integer> {
    boolean existsByUserIdAndPlateNumberAndStatus(Integer userId, String plateNumber, String status);
    List<Report> findByUserId(Integer userId);
    List<Report> findByUserIdAndStatusAndScoreGreaterThan(
            Integer userId, String status, Integer score);
}