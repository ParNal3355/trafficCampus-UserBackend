package org.example.charge.service;

import org.example.core.Response;
import org.example.core.JPA.entities.ChargingStation;
import org.example.core.JPA.repositories.ChargingStationRepository;
import org.example.core.JPA.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargeStationService {

    @Autowired
    private ChargingStationRepository stationRepository;
    private LocationRepository locationRepository;

    @Autowired
    private ChargeLocationService locationService;

    public Response getStationsByLocation(String location) {
        try {
            // 参数校验（新增数字格式验证）
            if (location == null || !location.matches("\\d+")) {
                return new Response(400, "INVALID_PARAMETER", "地点ID格式错误");
            }
            Integer locationId = Integer.parseInt(location);

            // 验证充电地点存在性（改为直接调用Repository）
            if (!locationRepository.existsById(locationId)) {
                return new Response(404, "LOCATION_NOT_FOUND", "充电地点不存在");
            }

            // 查询充电桩列表（改为使用locationId查询）
            List<ChargingStation> stations = stationRepository.findByLocation(location);

            // 转换为DTO格式
            List<Object> result = stations.stream().map(station -> new java.util.HashMap<String, Object>() {{
                put("stationID", station.getStationId());
                put("stationName", station.getStationName());
                put("installDate", new SimpleDateFormat("yyyy-MM-dd").format(station.getInstallDate()));
                put("feeStandard", station.getFeeStandard());
            }}).collect(Collectors.toList());

            return new Response(200, "SUCCEEDS", "查询成功", result);

        } catch (Exception e) {
            return new Response(500, "SYSTEM_ERROR", "系统繁忙，请稍后重试");
        }
    }

    private boolean isValidLocation(Integer locationId) {
        // 调用地点服务验证存在性
        Response response = locationService.getAllChargeLocations();
        return response.getCode() == 200 &&
                ((List<?>)response.getData()).stream()
                        .anyMatch(loc -> loc.toString().contains(locationId.toString()));
    }
}