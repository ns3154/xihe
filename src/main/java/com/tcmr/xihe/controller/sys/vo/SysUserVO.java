package com.tcmr.xihe.controller.sys.vo;

import lombok.Data;

/**
 * <p>
 *      系统用户
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 22:49
 **/
@Data
public class SysUserVO {

    private String token;

    private Integer role;

    private String roleName;
}
