package org.example.exam.jsonDTO;

import lombok.Data;

@Data
public class ExamRecordDTO {
    private Integer id;
    private Integer userId;
    private Integer score;
    private Long createTime;
}
