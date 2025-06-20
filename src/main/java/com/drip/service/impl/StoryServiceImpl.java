package com.drip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.entity.Story;
import com.drip.service.StoryService;
import com.drip.mapper.StoryMapper;
import org.springframework.stereotype.Service;

/**
* @author qq316
* @description 针对表【story(故事表)】的数据库操作Service实现
* @createDate 2025-04-12 10:15:49
*/
@Service
public class StoryServiceImpl extends ServiceImpl<StoryMapper, Story>
    implements StoryService{

}




