package com.drip.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 试卷表
 * @TableName papers
 */
@TableName(value ="papers")
@Data
public class Papers implements Serializable {
    /**
     * 试卷ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 试卷标题
     */
    private String title;

    /**
     * 试卷描述
     */
    private String description;

    /**
     * 试卷类型
     */
    private String type;

    /**
     * 试卷时长（分钟）
     */
    private Integer duration;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}