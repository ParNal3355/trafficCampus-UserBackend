package org.example.exam.jsonDTO;

import lombok.Data;

@Data
public class WrongAnswerRequest {
    private Integer questionId;
    private Integer selectedOptionId;
}