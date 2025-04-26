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
public class QuestionQueryService {
    private final QuestionRepository questionRepository;

    public QuestionQueryService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Response getQuestionsByType(String type, Integer count) {
        // 验证题目类型有效性
        List<String> validTypes = questionRepository.findDistinctCategories();
        if (!validTypes.contains(type)) {
            return new Response(400, "TYPE_NOT_SUPPORTED", "题目类型不存在", null);
        }

        // 验证数量参数
        if (count == null || count <= 0) {
            return new Response(400, "NUMBER_EMPTY", "Count值无效", null);
        }

        // 获取题目（示例使用内存随机，实际应使用数据库查询）
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(type, count);

        // 转换为DTO
        List<QuestionDTO> dtos = questions.stream()
                .map(q -> {
                    QuestionDTO dto = new QuestionDTO();
                    dto.setQuestionId(q.getQuestionId());
                    dto.setQuestionContent(q.getQuestionContent());
                    dto.setCategory(q.getCategory());

                    // 获取正确答案ID（假设answer字段存储正确选项ID）
                    dto.setAnswer(String.valueOf(Integer.parseInt(q.getAnswer())));

                    // 转换选项
                    List<OptionDTO> optionDTOs = q.getOptions().stream()
                            .map(opt -> new OptionDTO(opt.getExamId(), opt.getExamContent()))
                            .collect(Collectors.toList());
                    dto.setOptions(optionDTOs);

                    return dto;
                })
                .collect(Collectors.toList());

        return new Response(200, "SUCCESS", "获取成功", dtos);
    }
}
