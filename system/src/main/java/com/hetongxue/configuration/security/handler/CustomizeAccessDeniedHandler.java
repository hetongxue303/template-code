package com.hetongxue.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hetongxue.response.ResponseCode;
import com.hetongxue.response.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 无权访问处理器(已登录)
 * @ClassNmae: CustomizeAccessDeniedHandler
 * @Author: 何同学
 * @DateTime: 2022-08-25 22:53
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().println(new ObjectMapper().writeValueAsString(Result.Error().setCode(ResponseCode.FORBIDDEN.getCode()).setMessage("您没有权限访问，请联系管理员！")));
    }

}