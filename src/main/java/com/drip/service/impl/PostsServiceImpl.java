package com.drip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.entity.Posts;
import com.drip.service.PostsService;
import com.drip.mapper.PostsMapper;
import org.springframework.stereotype.Service;

/**
* @author qq316
* @description 针对表【posts】的数据库操作Service实现
* @createDate 2025-03-24 14:55:52
*/
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts>
    implements PostsService{

}




