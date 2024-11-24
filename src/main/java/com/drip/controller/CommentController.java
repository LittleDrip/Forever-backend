package com.drip.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.drip.domain.dto.CommentDTO;

import com.drip.service.CommentLikeService;
import com.drip.service.CommentService;
import com.drip.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author readpage
 * @since 2023-02-03 16:15
 */
@RestController
@Tag(name = "评论管理")
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private CommentLikeService commentLikeService;

    @Operation(summary = "根据文章id分页查询评论列表")
    @GetMapping("/page")
    public Result page(CommentDTO dto){
        return commentService.pageByAid(dto);
    }



    @Operation(summary = "添加评论")
    @PostMapping("/save")
    @SaCheckLogin
    public Result save(@RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(commentDTO);
    }

//    @Operation(summary = "删除评论")
//    @DeleteMapping("/remove/{id}")
//    public R remove(@PathVariable Integer id) {
//        return R.ok(commentService.removeById(id));
//    }

//    @Operation(summary = "评论点赞")
//    @PostMapping("/liked")
//    public R liked(@RequestBody LikedDTO likedDTO) {
//        User user = AuthUtils.get();
//        likedDTO.setUid(user.getId());
//        return R.ok(commentLikeService.liked(likedDTO));
//    }
//
//    @Operation(summary = "查询当前用户的点赞的评论id")
//    @GetMapping("/cidList/{uid}")
//    public R<List<Long>> cidList(@PathVariable Long uid) {
//        return R.ok(commentLikeService.cidListByUid(uid));
//    }
}



