package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 充电桩接口
 */

public interface ChargingStationRepository extends JpaRepository<ChargingStation, Integer> {

    List<ChargingStation> findByLocation(String location);
}
