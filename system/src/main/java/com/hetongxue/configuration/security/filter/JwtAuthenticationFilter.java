package com.hetongxue.configuration.security.filter;

import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.exception.JwtAuthenticationException;
import com.hetongxue.constant.Base;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.service.UserService;
import com.hetongxue.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Description: JWT过滤器
 * @ClassNmae: JwtAuthenticationFilter
 * @Author: 何同学
 * @DateTime: 2022-08-27 23:36
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader(Base.AUTHORIZATION_KEY);
        // 判断是否存在
        if (Objects.isNull(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 判断token是否合法
        Claims claims = jwtUtils.getClaims(token);
        if (Objects.isNull(claims)) {
            throw new JwtAuthenticationException("token凭证不合法！");
        }
        // 判断token是否过期
        String redisToken = String.valueOf(redisUtils.getValue(Base.AUTHORIZATION_KEY));
        if (Objects.isNull(redisToken) || jwtUtils.isExpired(claims)) {
            throw new JwtAuthenticationException("token凭证已过期！");
        }
        // 判断token与redis中的是否一致
        if (!Objects.equals(token, redisToken)) {
            throw new JwtAuthenticationException("token凭证发生异常！");
        }
        // 获取到当前用户ID
        String username = claims.getSubject();
        // 获取用户信息
        User user = userService.getUserByUsername(username);
        // TODO 获取用户权限信息(未完善)
        String authority = "";
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
        // 构建用户认证token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 放入security上下文中
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }

}