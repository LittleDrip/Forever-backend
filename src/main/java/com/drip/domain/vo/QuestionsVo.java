package com.drip.domain.vo;

import lombok.Data;

@Data
public class QuestionsVo {

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
     * 选项（JSON格式，适用于选择题）
     */
    private Object options;

}
