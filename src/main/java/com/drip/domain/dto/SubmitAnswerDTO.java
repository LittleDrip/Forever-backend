package com.drip.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubmitAnswerDTO {
    // 试卷ID
    private Long paperId;

    // 用户答案列表
    private List<String> answers;
}
