package com.drip.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.dto.LoginDTO;
import com.drip.domain.entity.User;
import com.drip.domain.vo.AuthorizeVO;
import com.drip.domain.vo.UserInfoVo;
import com.drip.service.UserService;
import com.drip.mapper.UserMapper;
import com.drip.util.Result;
import com.drip.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author qq316
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-11-22 20:43:23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public SaResult login(LoginDTO loginDTO) {
//        判断用户名是否存在
        User user = lambdaQuery().eq(User::getUsername, loginDTO.getUsername()).one();
        if (user == null || !user.getPassword().equals(loginDTO.getPassword())) {
            return SaResult.error("用户名或密码错误");
        } else {
            StpUtil.login(user.getId());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            long tokenTimeout = tokenInfo.getTokenTimeout();
//            计算到期时间
            Date expire = new Date(System.currentTimeMillis() + tokenTimeout * 1000);            AuthorizeVO authorizeVO=new AuthorizeVO();
            authorizeVO.setToken(tokenInfo.getTokenValue());
            authorizeVO.setExpire(expire);
            return SaResult.data(authorizeVO);
        }
    }

    @Override
    public Result getUserInfo() {
        String tokenValue = StpUtil.getTokenValue();
        if(StrUtil.isBlank(tokenValue)){
          return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        String id = StpUtil.getLoginIdByToken(tokenValue).toString();
        User user = getById(id);
        UserInfoVo userInfoVo = BeanUtil.copyProperties(user, UserInfoVo.class);
        return Result.ok(userInfoVo);
    }
}




