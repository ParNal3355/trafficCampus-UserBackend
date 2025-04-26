package org.example.report.jsonDTO;

public class ViolationLogDTO {
    private Integer reportId;
    private String plateNumber;
    private Long time;
    private String reportContent;
    private Integer score;

    // Getter/Setter
    public Integer getReportId() { return reportId; }
    public void setReportId(Integer reportId) { this.reportId = reportId; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public Long getTime() { return time; }
    public void setTime(Long time) { this.time = time; }

    public String getReportContent() { return reportContent; }
    public void setReportContent(String reportContent) { this.reportContent = reportContent; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}
