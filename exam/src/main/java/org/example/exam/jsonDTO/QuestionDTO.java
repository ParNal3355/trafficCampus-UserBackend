package org.example.exam.jsonDTO;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Integer questionId;
    private String questionContent;
    private String answer;  // 保持与表结构一致的字符串类型
    private String category;
    private List<OptionDTO> options;
}

