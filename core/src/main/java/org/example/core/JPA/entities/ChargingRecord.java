package org.example.core.JPA.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充电记录实体类
 */

@Entity
@Table(name = "charging_records")
public class ChargingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;//id

    @Column(name = "user_id")
    private Integer userId;//用户id

    @Column(name = "plate_id")
    private Integer plateId;//车牌id 无用字段

    @Column(name = "create_time")
    private Date Time;//创建时间

    @Column(name = "update_time")
    private Date updateTime;//更新时间 无用字段

    @Column(name = "end_time")
    private Date endTime;//结束时间

    private BigDecimal fee;//费用

    @Column(length = 10)
    private String status;//付款状态

    @JoinColumn(name = "location_id")
    private Integer location;//充电桩id

    // 无参构造
    public ChargingRecord() {}

    // 有参构造
    public ChargingRecord(Integer userId, Date Time,
                          Date endTime, BigDecimal fee, String status, Integer location) {
        this.userId = userId;
        this.Time = Time;
        this.endTime = endTime;
        this.fee = fee;
        this.status = status;
        this.location = location;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    // Getter & Setter
}