package com.tcmr.xihe.model;

import com.tcmr.xihe.protocol.enums.ResultEnum;
import com.tcmr.xihe.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 21:02
 **/
@Data
@AllArgsConstructor
public class ResultMessage<T> {


    private Integer code;

    private String message;

    private T data;

    public ResultMessage() {
        this(ResultEnum.OK);
    }

    public ResultMessage(Integer code, String messasge) {
        this.code = code;
        this.message = messasge;
    }

    public ResultMessage(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }

    public ResultMessage(BusinessException exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    public ResultMessage(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }


    public ResultMessage(T data) {
        this.code = ResultEnum.OK.getCode();
        this.message = ResultEnum.OK.getMessage();
        this.data = data;
    }

}
