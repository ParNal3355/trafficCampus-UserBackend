package org.example.exam.service;

import org.example.core.JPA.entities.Question;
import org.example.core.JPA.entities.WrongQuestion;
import org.example.core.JPA.repositories.QuestionRepository;
import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.JPA.repositories.WrongQuestionRepository;
import org.example.core.Response;
import org.example.exam.jsonDTO.OptionDTO;
import org.example.exam.jsonDTO.WrongQuestionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WrongQuestionService {
    private final WrongQuestionRepository wrongQuestionRepo;
    private final QuestionRepository questionRepo;
    private final SysUserRepository userRepo;

    public WrongQuestionService(WrongQuestionRepository wrongQuestionRepo,
                                QuestionRepository questionRepo,
                                SysUserRepository userRepo) {
        this.wrongQuestionRepo = wrongQuestionRepo;
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
    }

    public Response getWrongQuestions(Integer userId, String sort) {
        // 验证用户存在性
        if (!userRepo.existsById(userId)) {
            return new Response(404, "USER_NOT_FOUND", "用户ID不存在", null);
        }

        // 验证排序参数
        if (!"time".equalsIgnoreCase(sort) && !"type".equalsIgnoreCase(sort)) {
            return new Response(400, "SORT_PARAM_INVALID", "排序参数非time/type", null);
        }

        // 获取基础错题数据
        List<WrongQuestion> wrongQuestions = wrongQuestionRepo.findByUserId(userId);
        if (wrongQuestions.isEmpty()) {
            return new Response(204, "NO_WRONGS_FOUND", "用户无错题记录", null);
        }

        // 获取关联的题目信息
        List<Integer> questionIds = wrongQuestions.stream()
                .map(WrongQuestion::getQuestionId)
                .collect(Collectors.toList());
        List<Question> questions = questionRepo.findAllById(questionIds);

        // 构建DTO并排序
        List<WrongQuestionDTO> dtos = new ArrayList<>();
        for (int i = 0; i < wrongQuestions.size(); i++) {
            WrongQuestion wq = wrongQuestions.get(i);
            Question q = questions.get(i);

            WrongQuestionDTO dto = new WrongQuestionDTO();
            dto.setQuestionId(q.getQuestionId());
            dto.setQuestionContent(q.getQuestionContent());
            dto.setCategory(q.getCategory());
            dto.setAnswer(Integer.parseInt(q.getAnswer()));


            // 筛选正确选项和错误选项
            List<OptionDTO> filteredOptions = q.getOptions().stream()
                    .filter(opt -> opt.getExamId().equals(dto.getAnswer()) ||
                            opt.getExamId().equals(dto.getSelectedOption()))
                    .map(opt -> new OptionDTO(opt.getExamId(), opt.getExamContent()))
                    .collect(Collectors.toList());
            dto.setOptions(filteredOptions);

            dtos.add(dto);
        }

        // 执行排序
        if ("time".equalsIgnoreCase(sort)) {
            dtos.sort((a, b) -> b.getCreateTime().compareTo(a.getCreateTime()));
        } else {
            dtos.sort(Comparator.comparing(WrongQuestionDTO::getCategory));
        }

        return new Response(200, "SUCCESS", "获取成功", dtos);
    }
}
