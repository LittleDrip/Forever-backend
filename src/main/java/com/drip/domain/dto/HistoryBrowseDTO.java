package com.drip.domain.dto;

import lombok.Data;

@Data
public class HistoryBrowseDTO {

    private Long articleId;
    private String articleTitle;
    private Long userId;
}