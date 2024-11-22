package com.drip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.dto.CommentDTO;
import com.drip.domain.entity.Comment;
import com.drip.domain.entity.User;
import com.drip.mapper.CommentMapper;
import com.drip.mapper.UserMapper;
import com.drip.service.CommentService;
import com.drip.util.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author qq316
 * @description 针对表【comment】的数据库操作Service实现
 * @createDate 2024-11-22 20:43:12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public Result pageByAid(CommentDTO dto) {
        // 构造分页对象
        Page<Comment> page = new Page<>(dto.getCurrent(), dto.getSize());

        // 查询主评论（parentId = NULL）
        Page<Comment> commentPage = lambdaQuery()
                .isNull(Comment::getParentId)
                .eq(dto.getArticleId() != null, Comment::getArticleId, dto.getArticleId())
                .orderByDesc(Comment::getCreateTime)
                .page(page);

        // 获取主评论记录
        List<Comment> mainComments = commentPage.getRecords();
        if (mainComments.isEmpty()) {
            return Result.ok(formatResult(commentPage, List.of()));
        }

        // 获取主评论的ID集合
        List<Long> mainCommentIds = mainComments.stream().map(Comment::getId).toList();

        // 查询子评论
        List<Comment> allReplies = lambdaQuery()
                .in(Comment::getParentId, mainCommentIds)
                .orderByAsc(Comment::getCreateTime)
                .list();

        // 按 parentId 分组子评论
        Map<Long, List<Comment>> repliesGroupedByParentId = allReplies.stream()
                .collect(Collectors.groupingBy(Comment::getParentId));

        // 加载用户信息并格式化结果
        List<Map<String, Object>> formattedComments = mainComments.stream()
                .map(comment -> formatComment(comment, repliesGroupedByParentId))
                .toList();

        // 返回最终格式化结果
        return Result.ok(formatResult(commentPage, formattedComments));
    }

    @Override
    public Result saveComment(CommentDTO commentDTO) {
        // 模拟获取认证信息
        User user = userMapper.selectById(1);
        Comment comment = BeanUtil.copyProperties(commentDTO, Comment.class);
        comment.setUid(user.getId());
        comment.setUser(user);
        save(comment);
        return Result.ok(comment);
    }








//    工具方法实现

    private Map<String, Object> formatResult(Page<Comment> page, List<Map<String, Object>> comments) {
        Map<String, Object> result = new HashMap<>();
        result.put("total", page.getTotal());
        result.put("list", comments);
        return result;
    }

    private Map<String, Object> formatComment(Comment comment, Map<Long, List<Comment>> repliesGroupedByParentId) {
        Map<String, Object> formattedComment = new HashMap<>();
        formattedComment.put("id", comment.getId());
        formattedComment.put("parentId", comment.getParentId());
        formattedComment.put("articleId", comment.getArticleId());
        formattedComment.put("uid", comment.getUid());
        formattedComment.put("address", comment.getAddress());
        formattedComment.put("content", comment.getContent());
        formattedComment.put("likes", comment.getLikes());
        formattedComment.put("user", loadUserInfo(comment.getUid()));

        // 子评论
        List<Comment> replies = repliesGroupedByParentId.getOrDefault(comment.getId(), List.of());
        List<Map<String, Object>> formattedReplies = replies.stream()
                .map(reply -> {
                    Map<String, Object> formattedReply = formatComment(reply, Map.of());
                    formattedReply.put("reply", null); // 子评论的子评论为 null
                    return formattedReply;
                })
                .toList();

        Map<String, Object> replyData = new HashMap<>();
        replyData.put("total", replies.size());
        replyData.put("list", formattedReplies);

        formattedComment.put("reply", replyData);

        return formattedComment;
    }

    private Map<String, Object> loadUserInfo(Long uid) {
        User user = userMapper.selectById(uid);
        Map<String, Object> userMap = BeanUtil.beanToMap(user);
        return userMap;
    }
}




