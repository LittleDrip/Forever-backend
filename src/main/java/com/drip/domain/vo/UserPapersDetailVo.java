package com.drip.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserPapersDetailVo {

    /**
     * 评价内容
     */
    private String evaluation;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
