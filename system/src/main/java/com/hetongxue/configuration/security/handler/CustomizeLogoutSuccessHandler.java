package com.hetongxue.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.SpringSecurityConfiguration;
import com.hetongxue.response.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 注销成功处理器
 * @ClassNmae: LogoutSuccessHandler
 * @Author: 何同学
 * @DateTime: 2022-08-25 22:43
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 判断是否有权限信息 有的话删除
        if (!ObjectUtils.isEmpty(authentication)) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.OK.value());
        // 将token新信息设置为空
        response.setHeader(SpringSecurityConfiguration.AUTHORIZATION_KEY, "");
        // 清空redis中的token信息
        redisUtils.delete(SpringSecurityConfiguration.AUTHORIZATION_KEY);
        response.getWriter().println(new ObjectMapper().writeValueAsString(Result.Success().setMessage("注销成功")));
    }

}