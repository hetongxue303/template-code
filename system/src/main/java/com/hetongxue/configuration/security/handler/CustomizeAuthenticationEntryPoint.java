package com.hetongxue.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hetongxue.response.ResponseCode;
import com.hetongxue.response.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 匿名访问处理器(未登录)
 * @ClassNmae: CustomizeAuthenticationEntryPoint
 * @Author: 何同学
 * @DateTime: 2022-08-25 22:52
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(new ObjectMapper().writeValueAsString(Result.Error().setCode(ResponseCode.UNAUTHORIZED.getCode())
                .setMessage(authException.getMessage() == null ? "您还没有登录，请先登录！" : authException.getMessage())));
    }

}