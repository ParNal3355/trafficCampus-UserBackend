package org.example.core.JPA.entities;

import jakarta.persistence.*;
import java.util.Date;

/**
 * 举报日志实体类
 */

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer reportId;//id

    @Column(name = "user_id")
    private Integer userId;//举报人id

    @Column(name = "plate_id")
    private Integer plateId;//违规车牌id 无用选项

    @Column(name = "plate_number")
    private String plateNumber;//车牌号

    @Column(name = "create_time")
    private Date Time;//创建时间

    @Column(name = "update_time")
    private Date updateTime;//更新时间 无用选项

    @Column(name = "report_content")
    private String reportContent;//举报内容

    @Column(name = "status")
    private String status;//状态

    @Column(name = "score")
    private Integer score;//扣分分值

    // 无参构造方法
    public Report() {
    }

    // 有参构造方法
    public Report(Integer userId, String plateNumber, Date Time, String reportContent, String status, Integer score) {
        this.userId = userId;
        this.plateNumber = plateNumber;
        this.Time = Time;
        this.reportContent = reportContent;
        this.status = status;
        this.score = score;
    }

    // Getter 和 Setter 方法
    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date Time) {
        this.Time = Time;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}