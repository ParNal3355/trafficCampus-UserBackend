package org.example.core.JPA.entities;

import jakarta.persistence.*;

/**
 * 问题选项实体类
 */

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Integer examId;//选项id

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private Question question;//问题id

    @Column(name = "exam_content", columnDefinition = "TEXT")
    private String examContent;//内容

    // 无参构造方法
    public Exam() {
    }

    // 有参构造方法
    public Exam(Question question, String examContent) {
        this.question = question;
        this.examContent = examContent;
    }

    // Getter 和 Setter 方法
    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question questionId) {
        this.question = questionId;
    }

    public String getExamContent() {
        return examContent;
    }

    public void setExamContent(String examContent) {
        this.examContent = examContent;
    }
}
