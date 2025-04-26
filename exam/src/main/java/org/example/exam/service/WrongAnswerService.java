package org.example.exam.service;

import org.example.core.JPA.entities.WrongQuestion;
import org.example.core.JPA.repositories.QuestionRepository;
import org.example.core.JPA.repositories.WrongQuestionRepository;
import org.example.core.Response;
import org.example.exam.jsonDTO.WrongAnswerRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WrongAnswerService {
    private final QuestionRepository questionRepository;
    private final WrongQuestionRepository wrongQuestionRepo;

    public WrongAnswerService(QuestionRepository questionRepository,
                              WrongQuestionRepository wrongQuestionRepo) {
        this.questionRepository = questionRepository;
        this.wrongQuestionRepo = wrongQuestionRepo;
    }

    public Response submitWrongAnswers(Integer userId, String source, List<WrongAnswerRequest> wrongAnswers) {
        // 参数验证
        if (!Set.of("exam", "practice", "simulation").contains(source)) {
            return new Response(400, "SOURCE_INVALID", "题目类型参数错误", null);
        }

        if (wrongAnswers == null || wrongAnswers.isEmpty()) {
            return new Response(400, "EMPTY_ANSWERS", "错题列表不能为空", null);
        }

        // 检查题目存在性
        List<Integer> questionIds = wrongAnswers.stream()
                .map(WrongAnswerRequest::getQuestionId)
                .collect(Collectors.toList());

        if (questionRepository.countByQuestionIdIn(questionIds) != questionIds.size()) {
            return new Response(404, "QUESTION_NOT_EXIST", "部分题目不存在", null);
        }

        // 转换并保存记录
        List<WrongQuestion> records = wrongAnswers.stream().map(answer -> {
            WrongQuestion record = new WrongQuestion();
            record.setUserId(userId);
            record.setQuestionId(answer.getQuestionId());
            record.setSelectedOption(answer.getSelectedOptionId());
            record.setSource(source);
            return record;
        }).collect(Collectors.toList());

        wrongQuestionRepo.saveAll(records);

        return new Response(200, "SUCCESS", "错题记录保存成功", null);
    }
}
