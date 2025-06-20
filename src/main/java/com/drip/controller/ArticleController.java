package com.drip.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.drip.service.ArticleService;
import com.drip.util.Result;
import com.drip.util.page.PageParam;
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



    //    @SaCheckLogin
    // 分页查询
    @Tag(name = "分页查询文章")
    @PostMapping("/page")
    public Result getArticleByPage(@RequestBody PageParam pageParam){
        return articleService.getArticleByPage(pageParam);
    }

    @Tag(name = "获得总页数")
    @PostMapping("/pageTotal")
    public Result getArticleTotal(@RequestBody PageParam pageParam){
        return articleService.getTotals(pageParam);
    }

    @Tag(name="根据类型获取文章")
    @GetMapping("/tag")
    public Result getArticleByTag(@RequestParam("tag") String tag){
        return articleService.getArticleByTag(tag);
    }


    @Tag(name = "根据id获取文章")
    @GetMapping("/getArticle")
    public Result getArticleById(@RequestParam("id") Integer id){
        return articleService.getArticleById(id);
    }

    @Tag(name = "根据id获取文章并智能分析")
    @GetMapping("/summary")
    public Result getArticleConclusionById(@RequestParam("id") Integer id){
        return articleService.getArticleConclusionById(id);
    }
}
