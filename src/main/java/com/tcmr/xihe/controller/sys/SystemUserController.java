package com.tcmr.xihe.controller.sys;

import com.tcmr.xihe.controller.sys.req.LoginReq;
import com.tcmr.xihe.controller.sys.vo.SysUserVO;
import com.tcmr.xihe.model.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 22:48
 **/
@Api(value = "system", tags = "系统用户管理")
@Validated
@RequestMapping("system/user")
public class SystemUserController {

    @PostMapping("login")
    @ApiOperation("系统用户登录")
    public ResultMessage<SysUserVO> login(@Valid @RequestBody LoginReq req) {
        return new ResultMessage<>(new SysUserVO());
    }

}
