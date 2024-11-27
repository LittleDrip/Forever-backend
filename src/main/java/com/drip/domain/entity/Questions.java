package com.drip.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 题目表
 * @TableName questions
 */
@TableName(value ="questions")
@Data
public class Questions implements Serializable {
    /**
     * 题目ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 所属试卷ID
     */
    private Integer pid;

    /**
     * 题目内容
     */
    private String stcontent;

    /**
     * 题目类型（1、单选 2、多选 3、判断 4、简答）
     */
    private Integer questiontype;

    /**
     * 该题得分
     */
    private Integer stscore;

    /**
     * 正确答案（可以为空，选择题选项可能为空）
     */
    private String stanswer;

    /**
     * 选项（JSON格式，适用于选择题）
     */
    private Object options;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}