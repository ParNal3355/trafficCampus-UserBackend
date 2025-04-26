package org.example.plate.service;

import org.example.core.Response;
import org.example.core.JPA.entities.LicensePlate;
import org.example.core.JPA.repositories.LicensePlateRepository;
import org.example.core.JPA.repositories.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlateQueryService {

    @Autowired
    private SysUserRepository sysUserRepository;
    private LicensePlateRepository licensePlateRepository;

    public Response getUserPlates(Integer userId) {
        try {
            // 参数校验
            if (userId == null) {
                return new Response(400, "INCOMPLETE_PARAMETERS", "用户ID不能为空");
            }
            // 用户存在性校验
            if (!sysUserRepository.existsById(userId)) {
                return new Response(404, "USER_NOT_FOUND", "用户信息不存在");
            }

            // 查询用户所有车牌
            List<LicensePlate> plates = licensePlateRepository.findByUserId(userId);
            if (plates.isEmpty()) {
                return new Response(204, "NO_PLATES_FOUND", "未找到绑定车牌", Collections.emptyList());
            }
            // 转换为DTO格式
            List<Object> result = plates.stream().map(plate -> new java.util.HashMap<String, Object>() {{
                put("plateId", plate.getPlateId());
                put("plateName", plate.getPlateNumber());
                put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plate.getTime()));
                put("userId", plate.getUserId());
                put("vehicleType", plate.getVehicleType());
                put("score", plate.getScore());
                put("status", plate.getStatus());
            }}).collect(Collectors.toList());

            return new Response(200, "SUCCEEDS", "查询成功", result);

        } catch (Exception e) {
            return new Response(500, "SYSTEM_ERROR", "系统繁忙，请稍后重试");
        }
    }
}
