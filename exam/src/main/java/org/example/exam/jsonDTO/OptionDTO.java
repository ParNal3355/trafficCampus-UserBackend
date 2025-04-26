package org.example.exam.jsonDTO;

import lombok.Data;

@Data
public class OptionDTO {
    private Integer optionId;
    private String content;


    public OptionDTO(Integer examId, String examContent) {
    }
}
