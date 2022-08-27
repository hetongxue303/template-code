package com.hetongxue.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.SpringSecurityConfiguration;
import com.hetongxue.response.Result;
import com.hetongxue.system.domain.User;
import com.hetongxue.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 认证成功处理器
 * @ClassNmae: LoginSuccessHandler
 * @Author: 何同学
 * @DateTime: 2022-08-25 22:26
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 设置字符编码
        response.setContentType("application/json;charset=utf-8");
        // 设置状态
        response.setStatus(HttpStatus.OK.value());
        // 获取当前用户信息
        User user = (User) authentication.getPrincipal();
        // 生成token
        String token = jwtUtils.generateToken(user.getUsername());
        // 将token存于redis中(默认3天)
        redisUtils.setValue(SpringSecurityConfiguration.AUTHORIZATION_KEY, token, 7, TimeUnit.DAYS);
        // 将token设置在请求头上
        response.setHeader(SpringSecurityConfiguration.AUTHORIZATION_KEY, token);
        // 自定义返回内容
        response.getWriter().println(new ObjectMapper().writeValueAsString(Result.Success(user).setMessage("登陆成功")));
    }

}