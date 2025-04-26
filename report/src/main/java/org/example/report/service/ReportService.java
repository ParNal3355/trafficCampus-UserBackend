package org.example.report.service;

import org.example.core.JPA.repositories.LicensePlateRepository;
import org.example.core.Response;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class ReportService {
    private static final String PLATE_REGEX = "^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5}$";
    private final LicensePlateRepository plateRepository;

    public ReportService(LicensePlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    public Response validatePlate(String plateNo) {
        // 验证车牌格式
        if (!Pattern.matches(PLATE_REGEX, plateNo)) {
            return new Response(400, "PLATE_FORMAT_INVALID", "车牌格式错误", null);
        }

        // 检查车牌是否存在
        boolean exists = plateRepository.existsByPlateNumber(plateNo);

        return exists ?
                new Response(200, "SUCCESS", "车牌已登记", null) :
                new Response(404, "PLATE_NOT_REGISTERED", "车牌未登记", null);
    }
}