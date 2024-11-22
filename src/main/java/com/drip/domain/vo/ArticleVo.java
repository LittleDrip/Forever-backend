package com.drip.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ArticleVo {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 用户 ID
     */
    private Integer userId;

    /**
     * 类型：1--帖子，2--公告
     */
    private String type;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 阅读数
     */
    private Integer readCount;

    /**
     * 收藏数
     */
    private Integer collectCount;

}
