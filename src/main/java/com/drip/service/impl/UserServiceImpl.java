package com.drip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.entity.User;
import com.drip.service.UserService;
import com.drip.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author qq316
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-11-22 20:43:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




