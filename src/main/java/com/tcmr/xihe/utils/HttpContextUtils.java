package com.tcmr.xihe.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     http上下文工具类
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 20:03
 **/
public class HttpContextUtils {

    private HttpContextUtils () {
        // nothing
    }


    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}
