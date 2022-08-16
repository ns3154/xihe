package com.tcmr.xihe.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tcmr.xihe.config.JWTProperties;
import com.tcmr.xihe.exception.BusinessException;
import com.tcmr.xihe.model.JWTModel;
import com.tcmr.xihe.protocol.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * jwt
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 18:27
 **/
@Slf4j
@Component
public class JWTComponent {

    @Resource
    private JWTProperties jwtProperties;

    public String generateToken (JWTModel jwtModel) {
        return JWT.create()
                .withExpiresAt(DateUtils.addDays(new Date(), jwtProperties.getExpires()))
                .withPayload(jwtModel.covertMap())
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));
    }

    public JWTModel decrypt (String token) throws BusinessException {
        try {
            DecodedJWT decode = JWT.decode(token);
            return JWTModel.builder()
                    .sysUserId(decode.getClaim("sysUserId").asLong())
                    .sysUserName(decode.getClaim("sysUserName").asString())
                    .roleName(decode.getClaim("roleName").asString())
                    .build();
        } catch (JWTDecodeException exception) {
            log.error("jwt decrypt error, token: {}", token, exception);
            throw new BusinessException(ResultEnum.TOKEN_EXPIRES);
        }

    }


    @Test
    public void test () throws BusinessException {
        this.jwtProperties = new JWTProperties();
        jwtProperties.setExpires(3);
        jwtProperties.setSecret("223424234");
        String sign = generateToken(new JWTModel(1L, "32", "2342"));
        System.out.println(sign);


        sign = "sfsfsd";
        System.out.println(decrypt(sign));

    }




}
