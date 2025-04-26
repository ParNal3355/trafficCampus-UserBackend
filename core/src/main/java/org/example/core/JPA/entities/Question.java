package org.example.core.JPA.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 题库实体类
 */

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;//题目id


    @Column(name = "question_content",columnDefinition = "TEXT")
    private String questionContent;//题目内容

    @Column(name = "answer",columnDefinition = "TEXT")
    private String answer;//答案id

    @Column(name = "category")
    private String category;//题目类型

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Exam> options = new ArrayList<>();
    // 无参构造方法
    public Question() {
    }

    // 有参构造方法
    public Question(String questionContent, String answer, String category) {
        this.questionContent = questionContent;
        this.answer = answer;
        this.category = category;
    }

    // Getter 和 Setter 方法
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Exam> getOptions() {
        return options;
    }

    public void setOptions(List<Exam> options) {
        this.options = options;
    }
}
