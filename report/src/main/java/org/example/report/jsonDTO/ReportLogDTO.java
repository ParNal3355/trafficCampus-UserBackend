package org.example.report.jsonDTO;

public class ReportLogDTO {
    private Integer reportId;
    private String plateNumber;
    private Long time;
    private String reportContent;
    private String status;
    private Integer score;

    public Integer getReportId() {
        return reportId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public Long getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getReportContent() {
        return reportContent;
    }

    public Integer getScore() {
        return score;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
