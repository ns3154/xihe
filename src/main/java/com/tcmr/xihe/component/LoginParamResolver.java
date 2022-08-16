package com.tcmr.xihe.component;

import com.tcmr.xihe.annotation.Login;
import com.tcmr.xihe.protocol.constant.Constant;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  登录参数转换
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 19:20
 **/
@Component
public class LoginParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
        return req.getAttribute(Constant.GLOBAL_TOKEN_NAME);
    }
}
