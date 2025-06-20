package com.drip.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.drip.domain.entity.Posts;
import com.drip.service.PostsService;
import com.drip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }
//    添加帖子
    @PostMapping("/add")
    @SaCheckLogin
    public Result addPosts(@RequestBody Posts posts){
        postsService.save(posts);
        return Result.ok(null);
    }
     @GetMapping("getList")
    public Result<List<Posts>> getAllPosts() {
        return Result.ok(postsService.list());
    }

//    根据id获取
    @GetMapping("/get/{id}")
    public Result<Posts> getPostsById(@PathVariable Integer id){
        return Result.ok(postsService.getById(id));
    }

}
