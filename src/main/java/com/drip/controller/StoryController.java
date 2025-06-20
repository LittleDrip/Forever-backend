package com.drip.controller;

import com.drip.service.StoryService;
import com.drip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("story")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping("/list")
    public Result getAllStory(){
        return Result.ok(storyService.list());
    }
    @GetMapping("detail/{id}")
    public Result getStoryDetail(@PathVariable("id") Long id){
        return Result.ok(storyService.getById(id));
    }

}
