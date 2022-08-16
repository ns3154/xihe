package com.tcmr.xihe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 18:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTModel {

    private Long sysUserId;

    private String sysUserName;

    private String roleName;



    public Map<String, Object> covertMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("sysUserId" , this.sysUserId);
        map.put("sysUserName", this.sysUserName);
        map.put("roleName", this.roleName);
        return map;
    }


}
