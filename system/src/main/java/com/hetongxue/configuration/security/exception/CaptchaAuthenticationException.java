package com.hetongxue.configuration.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description: 验证码异常类
 * @ClassNmae: CaptchaAuthenticationException
 * @Author: 何同学
 * @DateTime: 2022-08-25 22:58
 */
public class CaptchaAuthenticationException extends AuthenticationException {

    public CaptchaAuthenticationException(String msg) {
        super(msg);
    }
}