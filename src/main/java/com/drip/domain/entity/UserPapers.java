package com.drip.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户与试卷的关联表，包含评价和做题状态
 * @TableName user_papers
 */
@TableName(value ="user_papers")
@Data
public class UserPapers implements Serializable {
    /**
     * 唯一标识符
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 试卷ID
     */
    private Integer paperId;

    /**
     * 评价内容
     */
    private String evaluation;

    /**
     * 做题状态 (0: 未做,1： 已完成)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}