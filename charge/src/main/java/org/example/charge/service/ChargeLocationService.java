package org.example.charge.service;

import org.example.core.JPA.entities.ChargingStation;
import org.example.core.JPA.repositories.ChargingStationRepository;
import org.example.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargeLocationService {

    @Autowired
    private ChargingStationRepository locationRepository;

    public Response getAllChargeLocations() {
        try {
            List<String> locations = locationRepository.findAll()
                    .stream()
                    .map(ChargingStation::getStationName)
                    .collect(Collectors.toList());

            return new Response(200, "SUCCEEDS", "查询成功", locations);

        } catch (Exception e) {
            return new Response(500, "SYSTEM_ERROR", "系统繁忙，请稍后重试");
        }
    }
}
