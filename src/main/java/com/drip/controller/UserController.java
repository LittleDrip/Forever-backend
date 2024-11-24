package com.drip.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.drip.domain.dto.LoginDTO;
import com.drip.service.UserService;
import com.drip.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "用户管理")
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Operation(summary = "登录")
    @PostMapping("login")
    public SaResult login(@RequestBody LoginDTO loginDTO) {
//实现登录逻辑
        return userService.login(loginDTO);
    }


    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @Operation(summary = "检测登录")
    @SaCheckLogin
    @GetMapping("isLogin")
    public String isLogin() {
        boolean login = StpUtil.isLogin();
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @SaCheckLogin
    @GetMapping("getUserInfo")
    public Result getUserInfo() {
        return userService.getUserInfo();
    }

}
