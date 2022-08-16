package com.tcmr.xihe.protocol.enums;

import lombok.Data;
import lombok.Getter;

/**
 * <p>
 *    返回结果枚举
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 21:07
 **/
@Getter
public enum ResultEnum {


    OK(0, "success"),
    BIND_ERROR(1, "非法入参"),
    SYSTEM_ERROR(-1, "system error"),
    TOKEN_EXPIRES(100, "登录过期,请重新登录"),
    ;
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;






}
