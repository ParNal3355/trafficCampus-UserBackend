package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 学法考试分数接口
 */

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findByUserId(Integer userId);
}
