package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.WrongQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户错题记录接口
 */

public interface WrongQuestionRepository extends JpaRepository<WrongQuestion, Integer> {
    List<WrongQuestion> findByUserId(Integer userId);
}