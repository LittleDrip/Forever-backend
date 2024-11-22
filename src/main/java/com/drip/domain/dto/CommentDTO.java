package com.drip.domain.dto;

import com.drip.util.page.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Schema(title = "CommentDto")
public class CommentDTO extends PageParam {

    @Schema(title = "文章id")
    private Long articleId;

    @Schema(title = "评论内容")
    private String content;

    @Schema(title = "评论父id")
    private Long parentId;

    public CommentDTO(int current, int size, Long parentId) {
        this.setCurrent(current);
        this.setSize(size);
        this.parentId = parentId;
    }
}
