package com.demo.provider.constant;

import lombok.AllArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/9
 * Time: 15:57
 * Description: No Description
 */
@AllArgsConstructor
public enum UserStatusCode implements IStatusCode{
    ERR_2000(2000,"用户信息不存在"),

    ERR_2001(2001,"用户昵称格式错误")
    ;
    // 状态码
    private Integer status;

    // 状态码描述
    private String msg;

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
