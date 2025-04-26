package org.example.main.api;

import org.example.core.Response;
import org.example.plate.service.*;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/plate")
public class PlateAPI {

    private final VehicleService vehicleService;
    private final PlateDeregistrationService deregistrationService;
    private final PlateQueryService queryService;

    public PlateAPI(VehicleService vehicleService,
                    PlateDeregistrationService deregistrationService,
                    PlateQueryService queryService) {
        this.vehicleService = vehicleService;
        this.deregistrationService = deregistrationService;
        this.queryService = queryService;
    }

    // 1. 车牌登记（参数需要包含时间戳）
    @PostMapping("/register")
    public Response registerPlate(
            @RequestParam String plateNumber,
            @RequestParam String vehicleType,
            @RequestParam Long createTime,  // 前端传时间戳
            @RequestParam Integer userId) {
        return vehicleService.registerVehicle(
                plateNumber,
                new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(new Date(createTime)),
                userId,
                vehicleType
        );
    }

    // 2. 车牌注销（需要用户ID验证）
    @DeleteMapping("/cancel/{plate}")
    public Response cancelPlate(
            @PathVariable String plate) {
        return deregistrationService.deregisterPlate(plate, 0);
    }

    // 3. 获取用户所有车牌
    @GetMapping("/user/{userId}")
    public Response getUserPlates(@PathVariable Integer userId) {
        return queryService.getUserPlates(userId);
    }
}