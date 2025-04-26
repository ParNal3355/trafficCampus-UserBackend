package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 题库接口
 */

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query(value = "SELECT * FROM questions ORDER BY RAND() LIMIT :count", nativeQuery = true)
    List<Question> findRandomQuestions(@Param("count") int count);

    @Query("SELECT DISTINCT q.category FROM Question q")
    List<String> findDistinctCategories();

    long countByQuestionIdIn(List<Integer> questionIds);

    @Query(value = "SELECT * FROM questions WHERE category = :category ORDER BY RAND() LIMIT :count",
            nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category,
                                                 @Param("count") int count);
}
