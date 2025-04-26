package org.example.report.service;

import org.example.core.JPA.entities.Report;
import org.example.core.JPA.repositories.ReportRepository;
import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.Response;
import org.example.report.jsonDTO.ReportLogDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportQueryService {
    private final ReportRepository reportRepository;
    private final SysUserRepository userRepository;

    public ReportQueryService(ReportRepository reportRepository,
                              SysUserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public Response getReportLogs(Integer userId) {
        // 验证用户存在性
        if (!userRepository.existsById(userId)) {
            return new Response(404, "USER_NOT_FOUND", "用户不存在", null);
        }

        // 查询举报记录
        List<Report> reports = reportRepository.findByUserId(userId);
        if (reports.isEmpty()) {
            return new Response(204, "NO_LOGS_FOUND", "无举报记录", null);
        }

        // 转换为DTO
        List<ReportLogDTO> dtos = reports.stream().map(report -> {
            ReportLogDTO dto = new ReportLogDTO();
            dto.setReportId(report.getReportId());
            dto.setPlateNumber(report.getPlateNumber());
            dto.setTime(report.getTime().getTime());
            dto.setReportContent(report.getReportContent());
            dto.setStatus(convertStatus(report.getStatus()));
            dto.setScore(report.getScore());
            return dto;
        }).collect(Collectors.toList());

        return new Response(200, "SUCCESS", "查询成功", dtos);
    }

    private String convertStatus(String statusCode) {
        return switch (statusCode) {
            case "0" -> "待处理";
            case "1" -> "已处理";
            default -> "未知状态";
        };
    }
}
