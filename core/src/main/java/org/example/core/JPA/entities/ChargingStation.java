package org.example.core.JPA.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充电桩实体类
 */

@Entity
@Table(name = "chargingStations")
public class ChargingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stationID")
    private Integer stationId;//充电桩id

    @Column(name = "stationName", nullable = false)
    private String stationName;//充电桩名称

    @Column(nullable = false)
    private String location;//充电桩位置，于充电地点表的location字段一致

    @Column(name = "installDate", nullable = false)
    private Date installDate;//安装日期

    @Column(name = "feeStandard")
    private BigDecimal feeStandard;//收费标准（元/时）

    // 无参构造
    public ChargingStation() {}

    // 有参构造（不含主键）
    public ChargingStation(String stationName, String location, Date installDate, BigDecimal feeStandard) {
        this.stationName = stationName;
        this.location = location;
        this.installDate = installDate;
        this.feeStandard = feeStandard;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public BigDecimal getFeeStandard() {
        return feeStandard;
    }

    public void setFeeStandard(BigDecimal feeStandard) {
        this.feeStandard = feeStandard;
    }
}