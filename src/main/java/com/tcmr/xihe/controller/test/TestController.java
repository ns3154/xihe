package com.tcmr.xihe.controller.test;

import com.tcmr.xihe.annotation.Login;
import com.tcmr.xihe.component.JWTComponent;
import com.tcmr.xihe.exception.BusinessException;
import com.tcmr.xihe.model.JWTModel;
import com.tcmr.xihe.model.ResultMessage;
import com.tcmr.xihe.dao.domain.TestDO;
import com.tcmr.xihe.dao.mapper.TestMapper;
import com.tcmr.xihe.protocol.constant.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 19:42
 **/
@Api(value = "test", tags = "测试模块")
@RestController
@RequestMapping("test")
@ConditionalOnProperty(prefix = "xihe.test", name = "api", havingValue = "true")
public class TestController {

    @Resource
    private JWTComponent jwtComponent;

    @Resource
    private TestMapper testMapper;

    @GetMapping("hello")
    @ApiOperation(value = "接口名称测试")
    public String hello() throws BusinessException {
        throw new BusinessException(1, "2342");
        //return "hello";
    }

    @GetMapping("insertTest")
    public ResultMessage<Long> insertTest (@Login JWTModel jwtModel) {
        TestDO test = new TestDO();
        test.setName("张三");
        test.setSex(1);
        test.setAge(122);
        testMapper.insertOrUpdate(test);
        return new ResultMessage<>(test.getId());
    }

    @PostMapping("write/header/cookie")
    public ResultMessage<Void> writeCookie(@ApiIgnore HttpServletResponse response) {
        JWTModel model = new JWTModel(1L, "test", "root_test");
        String token = jwtComponent.generateToken(model);
        response.addCookie(new Cookie(Constant.GLOBAL_TOKEN_NAME, token));
        response.addHeader(Constant.GLOBAL_TOKEN_NAME, token);
        return new ResultMessage<>();
    }
}
