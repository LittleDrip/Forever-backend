package com.drip.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName posts
 */
@TableName(value ="posts")
@Data
public class Posts implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String authorName;

    /**
     * 
     */
    private String authorAvatarUrl;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String category;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date createdAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}