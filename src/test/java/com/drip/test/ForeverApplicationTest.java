package com.drip.test;

import cn.dev33.satoken.stp.StpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class ForeverApplicationTest {

    @Test
    void testTime(){
        System.out.println("Current Date: " + new Date());
    }

    @Test
    public void testSaToken() {

        StpUtil.login(100);
        System.out.println(StpUtil.getTokenInfo());
        System.out.println("---------------------");

        System.out.println(StpUtil.getLoginId());
        System.out.println("---------------------");

    }

}