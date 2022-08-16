package com.tcmr.xihe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ns
 */
@SpringBootApplication
@MapperScan("com.tcmr.xihe.dao.mapper")
public class XiheApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiheApplication.class, args);
    }

}
