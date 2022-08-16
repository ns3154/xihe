package com.tcmr.xihe.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *    获取用户
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 20:56
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
@Inherited
public @interface Login {
}
