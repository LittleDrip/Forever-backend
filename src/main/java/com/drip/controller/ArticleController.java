package com.drip.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.drip.service.ArticleService;
import com.drip.util.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

//    @SaCheckLogin
    @Tag(name = "获得所有文章")
    @GetMapping("/list")
    public Result getAllArticle(){
        return articleService.getAllArticle();
    }

    @Tag(name = "根据id获取文章")
    @GetMapping("/getArticle")
    public Result getArticleById(@RequestParam("id") Integer id){
        return articleService.getArticleById(id);
    }
}
