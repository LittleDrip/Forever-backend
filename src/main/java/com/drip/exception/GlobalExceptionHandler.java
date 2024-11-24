package com.drip.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截 
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace(); 
        return SaResult.error(e.getMessage());
    }

    // 捕获 Sa-Token 的未登录异常
//    @ExceptionHandler(NotLoginException.class)
//    public SaResult handleNotLoginException(NotLoginException e) {
//        return SaResult.error("未登录，请先登录").setCode(401);
//    }
    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<String> handleNotLoginException(NotLoginException e) {
        // 返回状态码 401，错误提示信息
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED) // 设置 HTTP 状态码为 401
                .body("未登录，请先登录"); // 自定义错误信息
    }


}
