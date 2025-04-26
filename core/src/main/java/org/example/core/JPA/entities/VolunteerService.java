package org.example.core.JPA.entities;

import jakarta.persistence.*;
import java.util.Date;

/**
 * 志愿日志实体类
 */

@Entity
@Table(name = "volunteer_services")
public class VolunteerService {
    @Id
    @Column(name = "service_id")
    private Integer serviceId;//id

    @Column(name = "user_id")
    private Integer userId;//用户id

    @Column(name = "create_time")
    private Date Time;//创建时间

    @Column(name = "update_time")
    private Date updateTime;//更新时间 无用字段

    @Column(name = "service_location")
    private String serviceLocation;//志愿地点

    @Column(name = "description")
    private String description;//描述

    @Column(name = "plate_id")
    private Integer plateId;//车牌号id 无用字段

    @Column(name = "score")
    private Integer score;//加分分数

    @Column(name = "status")
    private String status;//状态

    // 无参构造方法
    public VolunteerService() {
    }

    // 有参构造方法
    public VolunteerService(Integer serviceId, Integer userId, Date Time, String serviceLocation, String description, Integer score, String status) {
        this.serviceId = serviceId;
        this.userId = userId;
        this.Time = Time;
        this.serviceLocation = serviceLocation;
        this.description = description;
        this.score = score;
        this.status = status;
    }

    // Getter 和 Setter 方法
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date Time) {
        this.Time = Time;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
