package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 充电地点接口
 */

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
