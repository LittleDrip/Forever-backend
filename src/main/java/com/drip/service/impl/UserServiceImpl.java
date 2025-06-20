package com.drip.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.dto.LoginDTO;
import com.drip.domain.dto.RegisterDTO;
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
import java.util.Random;

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
        // 存储用户信息到 Sa-Token 的会话中
        StpUtil.getSession().set("user", user);
        UserInfoVo userInfoVo = BeanUtil.copyProperties(user, UserInfoVo.class);
        return Result.ok(userInfoVo);
    }

    public  String generateNickname() {
        // 创建随机数生成器
        Random random = new Random();

        // 生成3个随机字母
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            char letter = (char) ('a' + random.nextInt(26)); // 'a' + [0, 25] = ['a', 'z']
            letters.append(letter);
        }

        // 生成3个随机数字
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int number = random.nextInt(10); // [0, 9]
            numbers.append(number);
        }

        // 拼接结果
        return "user_" + letters.toString() + numbers.toString();
    }
    @Override
    public Result register(RegisterDTO registerDTO) {
        if (registerDTO == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if (registerDTO.getUsername() == null || registerDTO.getPassword() == null ) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
//        检测password和confirmPassword是否一样
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }
//        检测是否已经注册过该账号
if (lambdaQuery().eq(User::getUsername, registerDTO.getUsername()).one() != null) {
         return Result.build(null, ResultCodeEnum.USERNAME_EXIST);
}
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setAvatar("https://pic1.imgdb.cn/item/6801e66d88c538a9b5daef83.png");
//        使用user_+"三位字符串，3个数字"生成昵称
        user.setNickname(generateNickname());
        userMapper.insert(user);
        return Result.ok(null);
    }

    @Override
    public Result updateNickname(String nickname) {
        String tokenValue = StpUtil.getTokenValue();
        if(StrUtil.isBlank(tokenValue)){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        String id = StpUtil.getLoginIdByToken(tokenValue).toString();
        User user = getById(id);
        user.setNickname(nickname);
        userMapper.updateById(user);
        return Result.ok(null);
    }

    @Override
    public Result updateNicknameAndAvatar(String nickname, String avatar) {
        String tokenValue = StpUtil.getTokenValue();
        if(StrUtil.isBlank(tokenValue)){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        String id = StpUtil.getLoginIdByToken(tokenValue).toString();
        User user = getById(id);
        if(nickname!=null){
            user.setNickname(nickname);
        }
        if(avatar!=null){
            user.setAvatar(avatar);
        }
        userMapper.updateById(user);
        return Result.ok(null);
    }
}




