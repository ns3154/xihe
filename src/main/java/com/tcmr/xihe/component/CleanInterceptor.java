package com.tcmr.xihe.component;

import com.tcmr.xihe.protocol.constant.Constant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 19:22
 **/
@Component
public class CleanInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        request.removeAttribute(Constant.GLOBAL_TOKEN_NAME);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
