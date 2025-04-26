package org.example.core.JPA.entities;

import jakarta.persistence.*;

/**
 * 用户错题记录实体类
 */

@Entity
@Table(name = "wrong_question")
public class WrongQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//id

    @Column(name = "user_id", nullable = false)
    private Integer userId;//用户id

    @Column(name = "question_id", nullable = false)
    private Integer questionId;//题目id

    @Column(name = "selected_option", nullable = false)
    private Integer selectedOption;//用户选择的选项id

    @Column(name = "source", nullable = false)
    private String source;//来源 practice（专项练习）/exam（学法考试）/simulation（模拟考试）

    // 无参构造方法
    public WrongQuestion() {
    }

    // 有参构造方法
    public WrongQuestion(Integer userId, Integer questionId, Integer selectedOption, String source) {
        this.userId = userId;
        this.questionId = questionId;
        this.selectedOption = selectedOption;
        this.source = source;
    }

    // Getter 和 Setter 方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
