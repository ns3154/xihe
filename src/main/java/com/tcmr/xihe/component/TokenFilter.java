package com.tcmr.xihe.component;

import com.tcmr.xihe.exception.BusinessException;
import com.tcmr.xihe.model.JWTModel;
import com.tcmr.xihe.protocol.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 21:18
 **/
@Slf4j
@Component
public class TokenFilter implements Filter {

    @Resource
    private JWTComponent jwtComponent;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(Constant.GLOBAL_TOKEN_NAME);

        // 尝试从cookie读取
        Cookie[] cookies = null;
        if (StringUtils.isBlank(token) && null != (cookies = request.getCookies())) {
            Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals(Constant.GLOBAL_TOKEN_NAME)).findFirst().orElse(null);
            if (null != cookie) {
                token = cookie.getValue();
            }
        }


        if (StringUtils.isNotBlank(token)) {
            try {
                JWTModel decrypt = jwtComponent.decrypt(token);
                request.setAttribute(Constant.GLOBAL_TOKEN_NAME, decrypt);
            } catch (BusinessException e) {
                throw new ServletException(e);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TokenFilter init ");
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
