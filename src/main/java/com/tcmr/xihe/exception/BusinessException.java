package com.tcmr.xihe.exception;

import com.tcmr.xihe.protocol.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 *    自定义异常
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 20:56
 **/
@Data
@AllArgsConstructor
public class BusinessException extends Exception {

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }


    private Integer code;
    private String message;

}
