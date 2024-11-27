package com.drip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.entity.UserPapers;
import com.drip.service.UserPapersService;
import com.drip.mapper.UserPapersMapper;
import org.springframework.stereotype.Service;

/**
* @author qq316
* @description 针对表【user_papers(用户与试卷的关联表，包含评价和做题状态)】的数据库操作Service实现
* @createDate 2024-11-26 19:50:45
*/
@Service
public class UserPapersServiceImpl extends ServiceImpl<UserPapersMapper, UserPapers>
    implements UserPapersService{

}




