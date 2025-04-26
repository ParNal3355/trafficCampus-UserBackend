package org.example.report.service;

import org.example.core.JPA.entities.Report;
import org.example.core.JPA.repositories.ReportRepository;
import org.example.core.JPA.repositories.LicensePlateRepository;
import org.example.core.Response;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class SubmitReportService {
    private final LicensePlateRepository plateRepository;
    private final ReportRepository reportRepository;

    public SubmitReportService(LicensePlateRepository plateRepository,
                               ReportRepository reportRepository) {
        this.plateRepository = plateRepository;
        this.reportRepository = reportRepository;
    }

    public Response submitReport(Integer userId, String plateNumber, String reportContent) {
        // 复用已有的车牌验证逻辑
        Response validation = validatePlate(plateNumber);
        if (validation.getCode() != 200) {
            return validation;
        }

        // 检查重复举报（状态为0表示未处理）
        boolean duplicate = reportRepository.existsByUserIdAndPlateNumberAndStatus(
                userId, plateNumber, "0");
        if (duplicate) {
            return new Response(409, "DUPLICATE_REPORT", "请勿重复提交相同车牌的举报", null);
        }

        // 创建举报记录
        Report report = new Report();
        report.setUserId(userId);
        report.setPlateNumber(plateNumber);
        report.setReportContent(reportContent);
        report.setTime(new Date());
        report.setStatus("0"); // 初始状态为待处理
        reportRepository.save(report);

        return new Response(200, "SUCCESS", "举报提交成功", null);
    }

    private Response validatePlate(String plateNumber) {
        // 复用原有的车牌验证逻辑
        if (plateNumber == null || !plateNumber.matches("^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5}$")) {
            return new Response(400, "PLATE_FORMAT_INVALID", "车牌格式错误", null);
        }
        return plateRepository.existsByPlateNumber(plateNumber) ?
                new Response(200, "SUCCESS", "验证通过", null) :
                new Response(404, "PLATE_NOT_REGISTERED", "车牌未登记", null);
    }
}