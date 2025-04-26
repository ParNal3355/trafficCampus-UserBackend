package org.example.charge.service;

import org.example.core.Response;
import org.example.core.JPA.entities.ChargingRecord;
import org.example.core.JPA.repositories.ChargingRecordRepository;
import org.example.core.JPA.repositories.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargeRecordService {

    @Autowired
    private ChargingRecordRepository recordRepository;

    @Autowired
    private SysUserRepository userRepository;

    public Response getUserChargeRecords(Integer userId) {
        try {
            // 参数校验
            if (userId == null) {
                return new Response(400, "INCOMPLETE_PARAMETERS", "用户ID不能为空");
            }

            // 用户存在性校验
            if (!userRepository.existsById(userId)) {
                return new Response(404, "USER_NOT_FOUND", "用户信息不存在");
            }

            // 查询充电记录
            List<ChargingRecord> records = recordRepository.findByUserId(userId);

            if (records.isEmpty()) {
                return new Response(204, "NO_RECORDS_FOUND", "无充电记录", Collections.emptyList());
            }

            // 转换为DTO格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Object> result = records.stream().map(record -> new java.util.HashMap<String, Object>() {{
                put("recordId", record.getRecordId());
                put("startTime", sdf.format(record.getTime()));
                put("endTime", sdf.format(record.getEndTime()));
                put("fee", record.getFee());
                put("status", record.getStatus());
                put("location", record.getLocation());
                //put("stationName", record.getStation().getStationName());
            }}).collect(Collectors.toList());

            return new Response(200, "SUCCEEDS", "查询成功", result);

        } catch (Exception e) {
            return new Response(500, "SYSTEM_ERROR", "系统繁忙，请稍后重试");
        }
    }
}
