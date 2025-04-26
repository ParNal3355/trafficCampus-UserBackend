package org.example.volunteer.service;

import org.example.core.JPA.entities.SysUser;
import org.example.core.JPA.entities.VolunteerService;
import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.JPA.repositories.VolunteerServiceRepository;
import org.example.core.Response;
import org.springframework.stereotype.Service;

import java.util.List;

// 用户志愿记录服务类
@Service
public class UserVolunteerRecordService {

    private SysUserRepository userRepository;
    private VolunteerServiceRepository serviceRepository;

    public Response getUserRecords(int userId) {
        try {
            // 参数校验
            if (userId == 0) {
                return new Response(400, "INCOMPLETE_PARAMETERS", "用户ID不能为空");
            }

            // 验证用户存在
            SysUser user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return new Response(404, "USER_NOT_FOUND", "用户不存在");
            }

            // 查询志愿记录（按serviceId倒序）
            List<VolunteerService> records = serviceRepository.findByUserIdOrderByTimeDesc(userId);

            return new Response(200, "SUCCEDS", "查询成功", records);

        } catch (Exception e) {
            return new Response(500, "SERVER_ERROR", "系统繁忙，请稍后重试");
        }
    }
}