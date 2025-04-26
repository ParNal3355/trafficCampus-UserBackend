package org.example.main.api;

import org.example.core.Response;
import org.example.report.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportAPI {

    private final ReportService reportService;
    private final SubmitReportService submitReportService;
    private final ReportQueryService reportQueryService;
    private final ViolationService violationService;

    public ReportAPI(ReportService reportService,
                     SubmitReportService submitReportService,
                     ReportQueryService reportQueryService,
                     ViolationService violationService) {
        this.reportService = reportService;
        this.submitReportService = submitReportService;
        this.reportQueryService = reportQueryService;
        this.violationService = violationService;
    }

    // 1. 图片上传（需补充文件上传实现）
    @GetMapping("/upload")
    public Response handleFileUpload(@RequestBody byte[] imageData) {
        // 需要集成图像识别服务
        return new Response(501, "NOT_IMPLEMENTED", "图片识别功能暂未实现");
    }

    // 2. 车牌检查
    @GetMapping("/check/{plateNo}")
    public Response checkPlate(@PathVariable String plateNo) {
        return reportService.validatePlate(plateNo);
    }

    // 3. 提交举报
    @PostMapping("/submit")
    public Response submitReport(
            @RequestParam Integer userId,
            @RequestParam String plateNumber,
            @RequestParam String report_content) {
        return submitReportService.submitReport(userId, plateNumber, report_content);
    }

    // 4. 获取举报记录
    @GetMapping("/logs/{userId}")
    public Response getReportLogs(@PathVariable Integer userId) {
        return reportQueryService.getReportLogs(userId);
    }

    // 5. 获取违章记录
    @GetMapping("/violations/{userId}")
    public Response getViolationLogs(@PathVariable Integer userId) {
        return violationService.getViolations(userId);
    }
}