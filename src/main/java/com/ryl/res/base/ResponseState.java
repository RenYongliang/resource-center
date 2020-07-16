package com.ryl.res.base;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-16 10:30:20
 */
public enum ResponseState {
    SUCCESS(200, "成功"),
    FAILURE(102, "失败"),
    USER_NEED_AUTHORITIES(201, "用户未登录"),
    USER_LOGIN_FAILED(202, "用户账号或密码错误"),
    USER_LOGIN_SUCCESS(203, "用户登录成功"),
    USER_NO_ACCESS(204, "用户无权访问"),
    USER_LOGOUT_SUCCESS(205, "用户登出成功"),
    TOKEN_IS_BLACKLIST(206, "此token已失效"),
    TOKEN_IS_UNLAWFUL(207, "此token已失效,请重新登录"),
    LOGIN_IS_OVERDUE(208, "登录已失效"),
    SERVER_LOGIC_ERROR(500, "服务端逻辑错误"),
    FORECASTING_ERROR(513, "可预判的错误!"),
    FORECASTING_UNIQUE_ERROR(514, "属性唯一错误!")
    ;


    private Integer code;
    private String message;

    ResponseState(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
