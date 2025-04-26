package org.example.volunteer.service;

import org.example.core.JPA.repositories.VolunteerPositionRepository;
import org.example.core.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
//查询志愿服务列表服务类
@Service
public class VolunteerQueryService {

    private VolunteerPositionRepository positionRepo;

    public Response getValidPositions(int page, int size) {
        try {
            // 参数校验逻辑
            if (page == 0 || size == 0) {
                return new Response(400, "INCOMPLETE_PARAMETERS", "必填参数为空");
            }
            if (page < 1 || size < 1) {
                return new Response(400, "PAGINATION_INVALID", "分页参数不合法");
            }

            // 执行分页查询（排除已关闭的岗位）
            var pageRequest = PageRequest.of(page - 1, size);
            var result = positionRepo.findByDeadlineAfterAndPositionStatusNotOrderByVolunteerPositionIdDesc(
                    new Date(),
                    "已关闭",
                    pageRequest
            );

            if (result.isEmpty()) {
                return new Response(204, "NO_EVENTS_FOUND", "无可用志愿活动");
            }

            return new Response(200, "SUCCEDS", "查询成功", result.getContent());

        } catch (Exception e) {
            return new Response(500, "SERVER_ERROR", "系统繁忙，请稍后重试");
        }
    }
}