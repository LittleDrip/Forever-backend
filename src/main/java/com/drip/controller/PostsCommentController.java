package com.drip.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.drip.domain.entity.PostsComment;
import com.drip.service.PostsCommentService;
import com.drip.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/postsComments")
public class PostsCommentController {

    @Resource
    private PostsCommentService postsCommentService;

    // 获取指定帖子的所有评论
    @GetMapping("/post/{postId}")
    public Result getCommentsByPostId(@PathVariable Long postId) {

        return Result.ok(postsCommentService.list(new LambdaQueryWrapper<PostsComment>()
                .eq(PostsComment::getPostId, postId)
                .orderByDesc(PostsComment::getCreatedAt)));
    }

    // 新增评论
    @PostMapping("add")
    public Result addComment(@RequestBody PostsComment postsComment) {
        return Result.ok(postsCommentService.save(postsComment));
    }

    // 删除评论
    @DeleteMapping("/{id}")
    public boolean deleteComment(@PathVariable Long id) {
        return postsCommentService.removeById(id);
    }
}
