package org.example.exam.jsonDTO;

import lombok.Data;

import java.util.List;

@Data
public class WrongQuestionDTO {
    private Integer questionId;
    private String questionContent;
    private Integer answer;
    private Integer selectedOption;
    private String category;
    private Long createTime;
    private List<OptionDTO> options;
}