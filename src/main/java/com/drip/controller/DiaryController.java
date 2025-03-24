package com.drip.controller;



import cn.dev33.satoken.annotation.SaCheckLogin;
import com.drip.domain.dto.DiaryDTO;
import com.drip.domain.entity.Diary;
import com.drip.domain.vo.DiaryVo;
import com.drip.service.DiaryService;
import com.drip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @SaCheckLogin
    @GetMapping("/list")
    public Result<List<DiaryVo>> getDiaryList() {
        List<DiaryVo> diaries= diaryService.getDiaryList();
      return Result.ok(diaries);
    }

    @SaCheckLogin
    @PostMapping("/create")
    public Result createDiary(@RequestBody DiaryDTO diary) {
        return diaryService.createDiary(diary);

    }

    @SaCheckLogin
    @PutMapping("/update")
    public Result<Void> updateDiary(@RequestParam Long id, @RequestBody DiaryDTO diary) {
        return diaryService.updateDiary(id, diary);
    }

    @SaCheckLogin
    @DeleteMapping("/delete")
    public Result<Void> deleteDiary(@RequestParam Long id) {
        return diaryService.deleteDiary(id);
    }
}