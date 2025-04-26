package org.example.core.JPA.entities;

import jakarta.persistence.*;
import java.util.Date;

/**
 * 志愿岗位实体类
 */

@Entity
@Table(name = "volunteer_positions")
public class VolunteerPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_position_id")
    private Integer volunteerPositionId;//ID

    @Column(name = "position_type")
    private String positionType;// 岗位类型

    @Column(name = "volunteer_location")
    private String volunteerLocation;// 志愿地点

    @Column(name = "job_content")
    private String jobContent;// 岗位内容

    @Column(name = "deadline")
    private Date deadline;// 截止时间

    @Column(name = "current_number")
    private Integer currentNumber;// 当前人数

    @Column(name = "required_number")
    private Integer requiredNumber;// 需求人数

    @Column(name = "position_status")
    private String positionStatus;// 岗位状态

    // 无参构造方法
    public VolunteerPosition() {
    }

    // 有参构造方法
    public VolunteerPosition(String positionType, String volunteerLocation, String jobContent, Date deadline, Integer currentNumber, Integer requiredNumber, String positionStatus) {
        this.positionType = positionType;
        this.volunteerLocation = volunteerLocation;
        this.jobContent = jobContent;
        this.deadline = deadline;
        this.currentNumber = currentNumber;
        this.requiredNumber = requiredNumber;
        this.positionStatus = positionStatus;
    }

    // Getter 和 Setter 方法
    public Integer getVolunteerPositionId() {
        return volunteerPositionId;
    }

    public void setVolunteerPositionId(Integer volunteerPositionId) {
        this.volunteerPositionId = volunteerPositionId;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getVolunteerLocation() {
        return volunteerLocation;
    }

    public void setVolunteerLocation(String volunteerLocation) {
        this.volunteerLocation = volunteerLocation;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Integer getRequiredNumber() {
        return requiredNumber;
    }

    public void setRequiredNumber(Integer requiredNumber) {
        this.requiredNumber = requiredNumber;
    }

    public String getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(String positionStatus) {
        this.positionStatus = positionStatus;
    }
}
