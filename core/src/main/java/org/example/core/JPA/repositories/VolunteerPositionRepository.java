package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.VolunteerPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * 志愿岗位接口
 */

public interface VolunteerPositionRepository extends JpaRepository<VolunteerPosition, Integer> {

    // 分页查询方法：截止时间大于当前时间，按ID倒序
    Page<VolunteerPosition> findByDeadlineAfterAndPositionStatusNotOrderByVolunteerPositionIdDesc(
            Date currentTime,
            String excludeStatus,
            Pageable pageable
    );

}
