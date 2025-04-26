package org.example.exam.service;

import org.example.core.JPA.entities.Score;
import org.example.core.JPA.repositories.ScoreRepository;
import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.Response;
import org.example.exam.jsonDTO.ExamRecordDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamRecordService {
    private final ScoreRepository scoreRepository;
    private final SysUserRepository userRepository;

    public ExamRecordService(ScoreRepository scoreRepository,
                             SysUserRepository userRepository) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
    }

    public Response getExamRecords(Integer userId) {
        // 验证用户存在性
        if (!userRepository.existsById(userId)) {
            return new Response(404, "USER_NOT_FOUND", "用户ID不存在", null);
        }

        List<Score> records = scoreRepository.findByUserId(userId);
        if (records.isEmpty()) {
            return new Response(204, "NO_RECORDS_FOUND", "用户无考试记录", null);
        }

        // 转换Score实体为DTO
        List<ExamRecordDTO> dtos = records.stream()
                .map(record -> {
                    ExamRecordDTO dto = new ExamRecordDTO();
                    dto.setId(record.getId());
                    dto.setUserId(record.getUserId());
                    dto.setScore(record.getScore());
                    dto.setCreateTime(record.getCreateTime().getTime()); // 转换为时间戳
                    return dto;
                })
                .collect(Collectors.toList());

        return new Response(200, "SUCCESS", "获取成功", dtos);
    }
}
