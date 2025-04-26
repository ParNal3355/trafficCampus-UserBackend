package org.example.report.service;

import org.example.core.JPA.entities.Report;
import org.example.core.JPA.repositories.ReportRepository;
import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.Response;
import org.example.report.jsonDTO.ViolationLogDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViolationService {
    private final ReportRepository reportRepository;
    private final SysUserRepository userRepository;

    public ViolationService(ReportRepository reportRepository,
                            SysUserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public Response getViolations(Integer userId) {
        // 验证用户存在性
        if (!userRepository.existsById(userId)) {
            return new Response(404, "USER_NOT_FOUND", "用户不存在", null);
        }

        // 查询已处理且扣分>0的记录
        List<Report> violations = reportRepository.findByUserIdAndStatusAndScoreGreaterThan(
                userId, "1", 0);

        if (violations.isEmpty()) {
            return new Response(404, "NO_VIOLATIONS", "无违章记录", null);
        }

        // 转换为DTO
        List<ViolationLogDTO> dtos = violations.stream().map(report -> {
            ViolationLogDTO dto = new ViolationLogDTO();
            dto.setReportId(report.getReportId());
            dto.setPlateNumber(report.getPlateNumber());
            dto.setTime(report.getTime().getTime());
            dto.setReportContent(report.getReportContent());
            dto.setScore(report.getScore());
            return dto;
        }).collect(Collectors.toList());

        return new Response(200, "SUCCESS", "查询成功", dtos);
    }
}
