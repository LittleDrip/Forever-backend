package com.drip.controller;


import com.drip.domain.dto.HistoryBrowseDTO;
import com.drip.domain.entity.HistoryBrowse;
import com.drip.service.HistoryBrowseService;
import com.drip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryBrowseController {

    @Autowired
    private HistoryBrowseService historyBrowseService;

    @PostMapping("/browse")
    public Result saveBrowseHistory(@RequestBody HistoryBrowseDTO BrowseDTO) {

        try {
            historyBrowseService.saveBrowseHistory(BrowseDTO);
            return Result.ok(null);
        } catch (Exception e) {
            // 记录错误日志
            e.printStackTrace();
            return Result.build(null,500,"保存历史浏览记录失败");
        }
    }

    @GetMapping("list/{userId}")
    public Result listBrowseHistory(@PathVariable  Long userId) {
        try {
            List<HistoryBrowse> browseHistory = historyBrowseService.getBrowseHistory(userId);
            return Result.ok(browseHistory);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(null,500,"获取历史浏览记录失败");
        }
    }
}