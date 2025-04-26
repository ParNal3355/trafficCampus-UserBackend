package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.ChargingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 充电记录接口
 */

public interface ChargingRecordRepository extends JpaRepository<ChargingRecord, Integer> {
    List<ChargingRecord> findByUserId(Integer userId);
    List<ChargingRecord> findByUserIdAndEndTimeIsNull(Integer userId);
}
