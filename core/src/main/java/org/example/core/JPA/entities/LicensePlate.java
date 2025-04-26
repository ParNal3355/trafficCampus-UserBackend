package org.example.core.JPA.entities;

import jakarta.persistence.*;
import java.util.Date;

/**
 * 车牌实体类
 */

@Entity
@Table(name = "license_plates")
public class LicensePlate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plate_id")
    private Integer plateId;//车牌id

    @Column(name = "plate_number", unique = true)
    private String plateNumber;//车牌号

    @Column(name = "create_time")
    private Date Time;//创建时间

    @Column(name = "update_time")
    private Date updateTime;//更新时间 无用选项

    @Column(name = "user_id")
    private Integer userId;//用户id

    @Column(name = "vehicle_type")
    private String vehicleType;//车辆类型

    @Column(name = "score")
    private Integer score;//车牌分数

    @Column(name = "status")
    private String status;//车牌状态 0：正常 1：冻结

    // 无参构造方法
    public LicensePlate() {
    }

    // 有参构造方法
    public LicensePlate(String plateNumber, Date Time, Integer userId, String vehicleType, Integer score, Integer status) {
        this.plateNumber = plateNumber;
        this.Time = Time;
        this.userId = userId;
        this.vehicleType = vehicleType;
        this.score = score;
        this.status = Integer.toString(status);
    }

    // Getter 和 Setter 方法
    public Integer getPlateId() {
        return plateId;
    }

    public void setPlateId(Integer plateId) {
        this.plateId = plateId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStatus() {
        return Integer.parseInt(status);
    }

    public void setStatus(Integer status) {
        this.status = Integer.toString(status);
    }
}
