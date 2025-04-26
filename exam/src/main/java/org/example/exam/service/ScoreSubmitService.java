package org.example.exam.service;

import org.example.core.JPA.entities.Score;
import org.example.core.JPA.repositories.ScoreRepository;
import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.Response;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScoreSubmitService {
    private final ScoreRepository scoreRepository;
    private final SysUserRepository userRepository;

    public ScoreSubmitService(ScoreRepository scoreRepository,
                            SysUserRepository userRepository) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
    }

    public Response submitScore(Integer userId, Integer scoreValue) {
        // 用户存在性校验
        if (!userRepository.existsById(userId)) {
            return new Response(404, "USER_NOT_FOUND", "用户ID不存在", null);
        }

        // 分数有效性校验
        if (scoreValue == null) {
            return new Response(400, "SCORE_INVALID", "分数不能为空", null);
        }
        if (scoreValue < 0 || scoreValue > 100) {
            return new Response(400, "SCORE_OUT_OF_RANGE", "分数不在0-100范围内", null);
        }

        // 创建分数记录
        Score score = new Score();
        score.setUserId(userId);
        score.setScore(scoreValue);
        score.setCreateTime(new Date());

        scoreRepository.save(score);

        return new Response(200, "SUCCESS", "分数提交成功", null);
    }
}
