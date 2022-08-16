package com.tcmr.xihe.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Contact;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 20:00
 **/
@Data
@Component
@ConfigurationProperties(prefix = "xihe.knife4j")
public class Knife4jProperties {

    private String title;

    private String description;

    private String serviceUrl;

    private String name;

    private String email;

    private String url;

    public Contact getContact() {
        return new Contact(name, url, email);
    }
}
