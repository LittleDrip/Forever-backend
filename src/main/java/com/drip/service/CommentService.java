package com.drip.service;

import com.drip.domain.dto.CommentDTO;
import com.drip.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drip.util.Result;

/**
* @author qq316
* @description 针对表【comment】的数据库操作Service
* @createDate 2024-11-22 20:43:12
*/
public interface CommentService extends IService<Comment> {

    Result pageByAid(CommentDTO dto);

    Result saveComment(CommentDTO commentDTO);
}
