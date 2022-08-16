package com.tcmr.xihe.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 20:47
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "xihe.log.aspect")
public class LogAspectProperties {

    /**
     * 计数是否开启
     * 并发并发高的时候 可进行动态关闭
     */
    private Boolean enableCount;

    private Set<String> excludeMethods;
}
