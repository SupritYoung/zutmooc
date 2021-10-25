package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//该自定义异常需要手动抛出
@Data
@AllArgsConstructor//生成有参构造方法
@NoArgsConstructor //生成无参构造方法
public class GuliException extends RuntimeException{
    //异常状态码
    private Integer code;
    //异常信息
    private String msg;
}
