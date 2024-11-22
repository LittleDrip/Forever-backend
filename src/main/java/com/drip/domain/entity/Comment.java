package com.drip.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 地址
     */
    private String address;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 创建时间
     */
    private Date createTime;

    // 用户
    @Schema(title = "用户")
    @TableField(exist = false)
    private User user;

    // 子评论列表
    @TableField(exist = false)
    private List<Comment> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}