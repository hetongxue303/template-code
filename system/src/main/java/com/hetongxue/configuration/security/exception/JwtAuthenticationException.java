
package com.hetongxue.configuration.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description: 验证码异常类
 * @ClassNmae: JwtAuthenticationException
 * @Author: 何同学
 * @DateTime: 2022-08-25 22:59
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

}