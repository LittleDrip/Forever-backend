package com.drip.controller;

import com.drip.domain.dto.SubmitAnswerDTO;
import com.drip.service.PapersService;
import com.drip.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("papers")
@Tag(name = "试卷管理")
public class PapersController {
    @Resource
    private PapersService papersService;
    @Operation(summary = "获取试卷列表")
    @GetMapping("list")
    public Result list() {
        return papersService.getList();
    }

    @Operation(summary = "用户提交答案")
    @PostMapping("submitAnswer")
    public Result submitAnswer(@RequestBody SubmitAnswerDTO submitAnswerDTO) {
        return papersService.getScoreAndSave(submitAnswerDTO);
    }

    @Operation(summary = "返回当前用户做过的试卷ID")
    @GetMapping("getPapers")
    public Result getPapers() {
        return papersService.getPapersByUser();
    }

    @Operation(summary = "获得当前试卷评价")
    @GetMapping("getEvaluation/{id}")
    public Result getEvaluation(@PathVariable("id") int id) {
        return papersService.getEvaluation(id);
    }
}
