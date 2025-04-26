package org.example.charge.service;

import org.example.charge.jsonDTO.ChargeSubmissionDTO;
import org.example.core.Response;
import org.example.core.JPA.entities.ChargingRecord;
import org.example.core.JPA.entities.ChargingStation;
import org.example.core.JPA.repositories.ChargingRecordRepository;
import org.example.core.JPA.repositories.ChargingStationRepository;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ChargeSubmissionService {

    private final ChargingRecordRepository recordRepository;
    private final ChargingStationRepository stationRepository;

    public ChargeSubmissionService(ChargingRecordRepository recordRepository,
                                   ChargingStationRepository stationRepository) {
        this.recordRepository = recordRepository;
        this.stationRepository = stationRepository;
    }

    public Response submitChargingRecord(ChargeSubmissionDTO dto) {
        try {
            // 参数校验
            if (dto.getStationID() == null || dto.getUserId() == null) {
                return new Response(400, "INCOMPLETE_PARAMETERS", "必要参数缺失");
            }

            // 获取充电桩并检查状态
            ChargingStation station = stationRepository.findById(dto.getStationID())
                    .orElse(null);

            if (station == null) {
                return new Response(404, "STATION_NOT_FOUND", "充电桩不存在");
            }


            // 创建充电记录
            ChargingRecord record = new ChargingRecord();
            record.setUserId(dto.getUserId());
            record.setTime(new Date(dto.getStartTime()));
            record.setEndTime(new Date(dto.getEndTime()));
            record.setFee(dto.getFee());
            record.setStatus("已付款");

            stationRepository.save(station);
            recordRepository.save(record);

            return new Response(200, "SUCCEEDS", "充电记录创建成功");

        } catch (Exception e) {
            return new Response(500, "SYSTEM_ERROR", "系统繁忙，请稍后重试");
        }
    }
}
