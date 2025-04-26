package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 车牌接口
 */

public interface LicensePlateRepository extends JpaRepository<LicensePlate, Integer> {

    boolean existsByPlateNumber(String plateNumber);
    Optional<LicensePlate> findByPlateNumber(String plateNumber);
    List<LicensePlate> findByUserId(Integer userId);
}
