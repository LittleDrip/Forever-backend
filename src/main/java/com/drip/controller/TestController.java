package com.drip.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acc/")
public class TestController {

    // 测试登录
//    @PostMapping("login")
//    public SaResult login(@RequestBody LoginDTO loginDTO) {
//        StpUtil.login(10001);
//        return SaResult.ok("登录成功");
//    }

    // 查询登录状态
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 测试注销
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }
}