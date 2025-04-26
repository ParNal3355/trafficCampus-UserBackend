package org.example.exam.service;

import org.example.core.JPA.entities.Question;
import org.example.core.JPA.repositories.QuestionRepository;
import org.example.core.Response;
import org.example.exam.jsonDTO.OptionDTO;
import org.example.exam.jsonDTO.QuestionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Response getRandomQuestions(int count) {
        // 参数验证
        if (count <= 0) {
            return new Response(400, "COUNT_INVALID", "题目数量必须大于0", null);
        }

        // 获取总题数
        long total = questionRepository.count();
        if (total < count) {
            return new Response(404, "QUESTIONS_INSUFFICIENT", "题库题目不足", null);
        }

        // 获取随机题目
        List<Question> questions = questionRepository.findRandomQuestions(count);
        List<QuestionDTO> dtos = questions.stream().map(q -> {
            QuestionDTO dto = new QuestionDTO();
            dto.setQuestionId(q.getQuestionId());
            dto.setQuestionContent(q.getQuestionContent());
            dto.setAnswer(q.getAnswer());
            dto.setCategory(q.getCategory());

            // 转换选项时匹配正确答案
            List<OptionDTO> optionDTOs = q.getOptions().stream()
                    .map(opt -> {
                        OptionDTO optDto = new OptionDTO(opt.getExamId(), opt.getExamContent());
                        optDto.setOptionId(opt.getExamId());
                        optDto.setContent(opt.getExamContent());
                        return optDto;
                    })
                    .collect(Collectors.toList());

            dto.setOptions(optionDTOs);
            return dto;
        }).collect(Collectors.toList());

        return new Response(200, "SUCCESS", "获取成功", dtos);
    }
}
