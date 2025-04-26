package org.example.core.JPA.entities;

import jakarta.persistence.*;
import java.util.Date;

/**
 * 学法考试分数实体类
 */

@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//id

    @Column(name = "user_id", nullable = false)
    private Integer userId;//用户id

    @Column(name = "score", nullable = false)
    private Integer score;//分数

    @Column(name = "createtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//创建时间

    // 无参构造方法
    public Score() {
    }

    // 有参构造方法
    public Score(Integer userId, Integer score,Date createTime) {
        this.userId = userId;
        this.score = score;
        this.createTime = createTime;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}