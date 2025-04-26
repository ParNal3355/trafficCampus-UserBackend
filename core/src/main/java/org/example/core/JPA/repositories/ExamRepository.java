package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 问题选项接口
 */
public interface ExamRepository extends JpaRepository<Exam, Integer> {

}