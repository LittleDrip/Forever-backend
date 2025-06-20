package com.drip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.entity.PostsComment;
import com.drip.service.PostsCommentService;
import com.drip.mapper.PostsCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author qq316
* @description 针对表【posts_comment】的数据库操作Service实现
* @createDate 2025-03-24 16:18:40
*/
@Service
public class PostsCommentServiceImpl extends ServiceImpl<PostsCommentMapper, PostsComment>
    implements PostsCommentService{

}




