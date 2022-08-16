package com.tcmr.xihe.component;

import com.alibaba.fastjson.JSON;
import com.tcmr.xihe.config.LogAspectProperties;
import com.tcmr.xihe.utils.HttpContextUtils;
import com.tcmr.xihe.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 19:54
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Resource
    private LogAspectProperties logAspectProperties;

    private static final AtomicLong BASE_COUNT = new AtomicLong();

    @Pointcut("@within(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMappingCut() {
    }


    @Around("requestMappingCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = point.getTarget().getClass().getName() + "." + signature.getName();
        if (logAspectProperties.getExcludeMethods().contains(methodName)) {
            return point.proceed();
        }

        Long curId = curCount();
        Method method = signature.getMethod();

        StringBuilder params = params(point, method);

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String httMethod = null;
        String ip = null;
        if (null != request) {
            httMethod = request.getMethod();
            ip = IPUtils.getIpAddr(request);
        }

        log.info("request curId: {}, ip: {}, http_method: {}, method:{}, reqParam:{}",
                curId, ip, httMethod, methodName, params);
        Object proceed = point.proceed();
        log.info("response curId: {}, res: {}", curId, JSON.toJSONString(proceed));
        return proceed;
    }

    @NotNull
    private StringBuilder params(ProceedingJoinPoint point, Method method) {
        Object[] args = point.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        StringBuilder params = new StringBuilder();
        if (args != null && paramNames != null) {
            for (int i = 0; i < args.length; i++) {
                Object param = args[i];
                if (ignoreParam(param)) {
                    continue;
                }
                params.append("  ").append(paramNames[i]).append(": ").append(param);
            }
        }
        return params;
    }

    private Long curCount() {
        if (Boolean.TRUE.equals(logAspectProperties.getEnableCount())) {
            return BASE_COUNT.addAndGet(1L);
        }
        return -1L;
    }

    private boolean ignoreParam(Object param) {
        return param instanceof ServletResponse || param instanceof ServletRequest || param instanceof MultipartFile;
    }
}
