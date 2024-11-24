package com.drip.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.util.SaResult;
import com.drip.domain.dto.LoginDTO;
import com.drip.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drip.util.Result;

/**
* @author qq316
* @description 针对表【user】的数据库操作Service
* @createDate 2024-11-22 20:43:23
*/
public interface UserService extends IService<User> {

    SaResult login(LoginDTO loginDTO);


    Result getUserInfo();
}
