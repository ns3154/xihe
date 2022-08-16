package com.tcmr.xihe.exception;

import com.tcmr.xihe.model.ResultMessage;
import com.tcmr.xihe.protocol.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

/**
 * <p>
 *    controller 全局异常拦截
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 20:59
 **/
@Slf4j
@RestControllerAdvice(basePackages = "com.tcmr.xihe.controller")
public class GlobalControllerExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler({BusinessException.class})
    public ResultMessage<Void> handleBusinessException(BusinessException e) {
        log.error("业务异常", e);
        return new ResultMessage<>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResultMessage<Void> handleAccessDeniedException(AccessDeniedException e) {
        log.error("系统异常", e);
        return new ResultMessage<>(ResultEnum.SYSTEM_ERROR);
    }

    /**
     * 处理绑定异常（通过Validation框架验证请求参数时的异常）
     */
    @ExceptionHandler(BindException.class)
    public ResultMessage<Void> handleBindException(BindException e) {
        log.error("数据绑定异常", e);
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return new ResultMessage<>(ResultEnum.BIND_ERROR.getCode(), message);
    }

    /**
     * 处理系统（其它）异常
     */
    @ExceptionHandler({Throwable.class})
    public ResultMessage<Void> handleSystemError(Throwable e) {
        log.error("系统异常", e);
        return new ResultMessage<>(ResultEnum.SYSTEM_ERROR);
    }



}
