package com.drip.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryVo {
    private Long id;
    private String Date;
    private String content; // 日记内容
    private String mood; // 心情
    private String weather; // 天气
}
