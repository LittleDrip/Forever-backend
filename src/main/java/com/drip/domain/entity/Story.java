package com.drip.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 故事表
 * @TableName story
 */
@TableName(value ="story")
@Data
public class Story implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 故事内容
     */
    private String content;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 故事类型
     */
    private String type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}