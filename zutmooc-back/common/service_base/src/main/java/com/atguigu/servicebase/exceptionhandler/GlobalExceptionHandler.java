package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//执行过程：先找特定异常，否则则执行全局异常
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //全局异常处理
    //@ExceptionHandler指定出现什么异常会执行这个方法
    @ExceptionHandler(Exception.class) //Exception.class表示所有异常
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    //特定异常处理（以除0错误为例）
    @ExceptionHandler(ArithmeticException.class) //Exception.class表示所有异常
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了算数错误异常处理");
    }

    //自定义异常处理（以除0错误为例）
    @ExceptionHandler(GuliException.class) //Exception.class表示所有异常
    @ResponseBody
    public R error(GuliException e){
        log.error(e.getMessage()); //将错误信息输出到日志中
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
