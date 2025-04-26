package org.example.charge.jsonDTO;

import java.math.BigDecimal;

public class ChargeSubmissionDTO {
    private Integer userId;
    private Long startTime;
    private Long endTime;
    private BigDecimal fee;
    private String status;
    private Integer stationID;

    // Getters and Setters
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Long getStartTime() { return startTime; }
    public void setStartTime(Long startTime) { this.startTime = startTime; }
    public Long getEndTime() { return endTime; }
    public void setEndTime(Long endTime) { this.endTime = endTime; }
    public BigDecimal getFee() { return fee; }
    public void setFee(BigDecimal fee) { this.fee = fee; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getStationID() { return stationID; }
    public void setStationID(Integer stationID) { this.stationID = stationID; }
}
