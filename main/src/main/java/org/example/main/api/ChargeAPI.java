package org.example.main.api;

import org.example.charge.jsonDTO.ChargeSubmissionDTO;
import org.example.core.Response;
import org.example.charge.service.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/charge")
public class ChargeAPI {

    private final ChargeLocationService locationService;
    private final ChargeStationService stationService;
    private final ChargeRecordService recordService;
    private final ChargeSubmissionService submissionService;

    // 构造函数注入
    public ChargeAPI(ChargeLocationService locationService,
                     ChargeStationService stationService,
                     ChargeRecordService recordService,
                     ChargeSubmissionService submissionService) {
        this.locationService = locationService;
        this.stationService = stationService;
        this.recordService = recordService;
        this.submissionService = submissionService;
    }

    // 1. 获取所有充电地点
    @GetMapping("/locations")
    public Response getAllLocations() {
        return locationService.getAllChargeLocations();
    }

    // 2. 获取某地点充电桩
    @GetMapping("/piles/{locationId}")
    public Response getPilesByLocation(@PathVariable String locationId) {
        return stationService.getStationsByLocation(locationId);
    }

    // 3. 获取用户充电记录
    @GetMapping("/records/{userId}")
    public Response getUserRecords(@PathVariable Integer userId) {
        return recordService.getUserChargeRecords(userId);
    }

    // 4. 提交充电信息
    @PostMapping("/start")
    public Response submitCharge(
            @RequestParam Integer userId,
            @RequestParam Long startTime,
            @RequestParam Long endTime,
            @RequestParam BigDecimal fee,
            @RequestParam String status,
            @RequestParam Integer stationID) {

        // 将参数封装到DTO对象
        ChargeSubmissionDTO dto = new ChargeSubmissionDTO();
        dto.setUserId(userId);
        dto.setStartTime(startTime);
        dto.setEndTime(endTime);
        dto.setFee(fee);
        dto.setStatus(status);
        dto.setStationID(stationID);
        return submissionService.submitChargingRecord(dto);
    }
}