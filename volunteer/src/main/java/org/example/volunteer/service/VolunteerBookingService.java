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
//志愿预约服务类
@Service
public class VolunteerBookingService {
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String EVENT_NOT_EXIST = "EVENT_NOT_EXIST";
    private static final String EVENT_FULL = "EVENT_FULL";

    private SysUserRepository userRepository;
    private VolunteerPositionRepository positionRepository;
    private VolunteerServiceRepository serviceRepository;

    @Transactional
    public Response bookVolunteer(int userId, int volunteerId) {
        try {
            if (userId == 0 || volunteerId == 0) {
                return new Response(400, "INCOMPLETE_PARAMETERS", "必填参数为空");
            }

            SysUser user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return new Response(404, USER_NOT_FOUND, "用户不存在");
            }

            VolunteerPosition position = positionRepository.findById(volunteerId).orElse(null);
            if (position == null) {
                return new Response(404, EVENT_NOT_EXIST, "志愿岗位不存在");
            }

            if (position.getCurrentNumber() >= position.getRequiredNumber()) {
                return new Response(409, EVENT_FULL, "该岗位已报满");
            }
            if (position.getDeadline().before(new Date())) {
                return new Response(404, EVENT_NOT_EXIST, "该岗位已截止");
            }

            // 创建志愿记录
            VolunteerService serviceRecord = new VolunteerService();
            serviceRecord.setServiceId(position.getVolunteerPositionId());
            serviceRecord.setUserId(userId);
            serviceRecord.setServiceLocation(position.getVolunteerLocation());
            serviceRecord.setDescription(position.getJobContent());
            serviceRecord.setScore(0);
            serviceRecord.setStatus("已预约");
            serviceRecord.setTime(new Date());
            serviceRepository.save(serviceRecord);

            // 更新岗位人数
            position.setCurrentNumber(position.getCurrentNumber() + 1);
            positionRepository.save(position);


            return new Response(200, "SUCCEDS", "预约成功");

        } catch (Exception e) {
            return new Response(500, "SERVER_ERROR", "系统繁忙，请稍后重试");
        }
    }
}