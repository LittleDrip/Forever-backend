package com.drip.controller;

import com.drip.domain.dto.SubmitAnswerDTO;
import com.drip.service.QuestionsService;
import com.drip.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("questions")
@Tag(name="题目管理")
public class QuestionsController {

    @Resource
    private QuestionsService questionsService;

    @GetMapping("/{pid}")
    @Operation(summary = "根据试卷id获取题目")
    public Result getQuestionsByPId(@PathVariable("pid") int pid) {
        return questionsService.getQuestionsByPId(pid);
    }



}
