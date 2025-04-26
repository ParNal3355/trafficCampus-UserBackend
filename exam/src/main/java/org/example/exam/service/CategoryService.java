package org.example.exam.service;

import org.example.core.JPA.repositories.QuestionRepository;
import org.example.core.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final QuestionRepository questionRepository;

    public CategoryService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Response getAllCategories() {
        try {
            List<String> categories = questionRepository.findDistinctCategories();
            return new Response(200, "SUCCESS", "获取成功", categories);
        } catch (Exception e) {
            return new Response(500, "SERVER_ERROR", "服务器内部错误", null);
        }
    }
}
