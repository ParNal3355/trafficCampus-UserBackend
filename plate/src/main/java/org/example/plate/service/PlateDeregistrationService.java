package org.example.plate.service;

import org.example.core.Response;
import org.example.core.JPA.entities.LicensePlate;
import org.example.core.JPA.repositories.LicensePlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PlateDeregistrationService {

    @Autowired
    private LicensePlateRepository licensePlateRepository;

    public Response deregisterPlate(String plateNumber, Integer userId) {
        try {
            // 参数完整性校验
            if (plateNumber == null || plateNumber.isEmpty() || userId == null) {
                return new Response(400, "INCOMPLETE_PARAMETERS", "参数不完整");
            }

            // 查询车牌信息
            Optional<LicensePlate> plateOpt = licensePlateRepository.findByPlateNumber(plateNumber);
            if (plateOpt.isEmpty()) {
                return new Response(404, "PLATE_NOT_FOUND", "车牌不存在");
            }

            LicensePlate plate = plateOpt.get();

            // 验证用户所有权
            if (!plate.getUserId().equals(userId)) {
                return new Response(403, "OWNER_MISMATCH", "用户与车牌登记信息不符");
            }

            // 执行注销操作
            licensePlateRepository.delete(plate);

            return new Response(200, "SUCCEEDS", "注销成功", plate);

        } catch (Exception e) {
            return new Response(500, "SYSTEM_ERROR", "系统繁忙，请稍后重试");
        }
    }
}
