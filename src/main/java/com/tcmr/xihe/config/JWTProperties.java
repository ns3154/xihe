package com.tcmr.xihe.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *      jwt动态配置参数
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 18:35
 **/
@Data
@Component
@ConfigurationProperties(prefix = "xihe.jwt")
public class JWTProperties {

    private String secret;

    private Integer expires;
}
