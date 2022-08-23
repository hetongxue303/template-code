package com.hetongxue.system.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 登录DTO
 * @ClassNmae: LoginDto
 * @Author: 何同学
 * @DateTime: 2022-08-16 22:17
 */
@Data
@Accessors(chain = true)
public class LoginDto implements Serializable {

    private String username;
    private String password;
    private String code;
    private Boolean rememberMe;

}