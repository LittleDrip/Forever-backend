package com.drip.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDTO {
    private String content; // 日记内容
    private String mood; // 心情
    private String weather; // 天气
}
