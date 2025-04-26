package org.example.exam.service;

import org.example.core.JPA.entities.LicensePlate;
import org.example.core.JPA.repositories.LicensePlateRepository;
import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    private final LicensePlateRepository licensePlateRepository;
    private final SysUserRepository userRepository;

    public ScoreService(LicensePlateRepository licensePlateRepository,
                        SysUserRepository userRepository) {
        this.licensePlateRepository = licensePlateRepository;
        this.userRepository = userRepository;
    }

    public Response addUserScore(Integer userId, Integer fraction) {
        // 参数验证
        if (fraction == null || fraction <= 0 || fraction > 100) {
            return new Response(400, "FRACTION_INVALID", "分数值无效", null);
        }

        // 验证用户存在
        if (!userRepository.existsById(userId)) {
            return new Response(404, "USER_NOT_FOUND", "用户不存在", null);
        }

        // 获取用户关联的车牌
        List<LicensePlate> plates = licensePlateRepository.findByUserId(userId);
        if (plates.isEmpty()) {
            return new Response(404, "PLATE_NOT_FOUND", "用户未绑定车牌", null);
        }

        // 更新所有关联车牌的分数（或根据业务需求选择主车牌）
        plates.forEach(plate -> {
            int newScore = plate.getScore() + fraction;
            plate.setScore(Math.min(newScore, 100)); // 分数上限100
        });
        licensePlateRepository.saveAll(plates);

        return new Response(200, "SUCCESS", "分数更新成功", null);
    }
}
