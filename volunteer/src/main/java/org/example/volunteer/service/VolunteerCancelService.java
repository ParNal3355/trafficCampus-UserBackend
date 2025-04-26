package org.example.volunteer.service;

import org.example.core.JPA.entities.SysUser;
import org.example.core.JPA.entities.VolunteerPosition;
import org.example.core.JPA.entities.VolunteerService;
import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.JPA.repositories.VolunteerPositionRepository;
import org.example.core.JPA.repositories.VolunteerServiceRepository;
import org.example.core.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

//志愿服务取消服务类
@Service
public class VolunteerCancelService {

    private SysUserRepository userRepository;
    private VolunteerServiceRepository serviceRepository;
    private VolunteerPositionRepository positionRepository;

    @Transactional
    public Response cancelVolunteer(int userId, int volunteerPositionId) {
        try {
            // 参数校验
            if (userId == 0 || volunteerPositionId == 0) {
                return new Response(400, "INCOMPLETE_PARAMETERS", "参数不能为空");
            }

            // 验证用户存在
            SysUser user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return new Response(404, "USER_NOT_FOUND", "用户不存在");
            }

            // 查询关联记录（通过新设计的serviceId）
            VolunteerService service = serviceRepository.findByServiceIdAndUserId(volunteerPositionId, userId);
            if (service == null) {
                return new Response(404, "RESERVATION_NOT_FOUND", "未找到预约记录");
            }

            // 获取志愿岗位
            VolunteerPosition position = positionRepository.findById(volunteerPositionId).orElse(null);
            if (position == null) {
                return new Response(404, "EVENT_NOT_EXIST", "志愿岗位不存在");
            }

            // 检查截止时间
            if (new Date().after(position.getDeadline())) {
                return new Response(423, "EVENT_STARTED", "活动已开始无法取消");
            }

            // 更新岗位人数
            position.setCurrentNumber(position.getCurrentNumber() - 1);
            positionRepository.save(position);

            // 更新服务状态
            service.setStatus("已取消");
            serviceRepository.save(service);

            return new Response(200, "SUCCESS", "取消成功");

        } catch (Exception e) {
            return new Response(500, "SERVER_ERROR", "系统繁忙，请稍后重试");
        }
    }
}