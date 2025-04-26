package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.VolunteerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * 志愿服务接口
 */

public interface VolunteerServiceRepository extends JpaRepository<VolunteerService, Integer> {
    //用户记录查询方法
    @Query("SELECT vs FROM VolunteerService vs WHERE vs.userId = ?1 ORDER BY vs.Time DESC")
    List<VolunteerService> findByUserIdOrderByTimeDesc(Integer userId);

    VolunteerService findByServiceIdAndUserId(Integer serviceId, Integer userId);
}
