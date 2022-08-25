package com.hetongxue.configuration.security;

import com.hetongxue.configuration.security.handler.*;
import com.hetongxue.system.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

/**
 * @Description: SpringSecurity配置类
 * @ClassNmae: SpringSecurityConfiguration
 * @Author: 何同学
 * @DateTime: 2022-08-25 21:01
 */
@Configuration
public class SpringSecurityConfiguration {

    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private CustomizeLogoutSuccessHandler logoutSuccessHandler;
    @Resource
    private CustomizeAccessDeniedHandler accessDeniedHandler;
    @Resource
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private CustomizeExpiredSessionStrategy expiredSessionStrategy;

    /**
     * 配置参数
     */
    public static final String LOGIN_PATH = "/auth/login";
    public static final String LOGOUT_PATH = "/auth/logout";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final Integer MAXIMUM_SESSIONS = 1;
    public static final String CAPTCHA_KEY = "captcha";
    public static final String AUTHORIZATION_KEY = "authorization";
    public static final String CODE_KEY = "code";
    public static final String REMEMBER_ME_KEY = "rememberMe";

    /**
     * 放行白名单
     */
    private final String[] REQUEST_WHITE_LIST = {"/auth/login", "/auth/getCode"};

    /**
     * UserDetailsService接口的实现类
     */
    @Resource
    private UserServiceImpl userService;

    /**
     * 注入密码加密处理
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置安全过滤器链
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 放行白名单请求 其余请求全部拦截
        http.authorizeRequests().antMatchers(REQUEST_WHITE_LIST).permitAll().anyRequest().authenticated();

        // 设置表单登录 设置登录请求地址
        http.formLogin().loginProcessingUrl(LOGIN_PATH)
                // 设置登陆 -> 请求参数
                .usernameParameter(USERNAME).passwordParameter(PASSWORD)
                // 设置认证成功处理器 和 认证失败处理器
                .successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
                // 开启注销登录 注销请求地址 设置注销成功处理类
                .and().logout().logoutUrl(LOGOUT_PATH).logoutSuccessHandler(logoutSuccessHandler);

        // 开启异常处理
        http.exceptionHandling()
                // 设置匿名用户处理类(未登录 需要跳转登陆) 无权访问处理类(已登录 提示用户无权限)
                .authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);

        // 开启跨域 以及对应配置
        http.cors().configurationSource(corsConfigurationSource())
                // 关闭csrf攻击
                .and().csrf().disable();

        // 开启会话管理
        http.sessionManagement()
                // 设置创建会话策略
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 设置当前用户可以创建的最大会话数量(默认1)
                .maximumSessions(MAXIMUM_SESSIONS)
                // 设置会话过期处理类
                .expiredSessionStrategy(expiredSessionStrategy)
                // 设置阻止登录策略 true:禁止再次登录  false(默认):登陆时会将前一次登录的设备挤下线
                .maxSessionsPreventsLogin(true);

        // 添加jwt自动登录过滤器
//        http.addFilter(jwtAuthenticationFilter())
//                // 添加在UsernamePasswordAuthenticationFilter之前的captchaFilter(登陆之前)
//                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

        return http.httpBasic(Customizer.withDefaults()).build();
    }

    /**
     * 配置认证处理器
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());// 设置密码编码器
        authenticationProvider.setUserDetailsService(userService);// 设置用户详细信息服务
        // 这里要隐藏系统默认的提示信息，否则一直显示账户或密码错误
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    /**
     * 解决添加security后 springboot自身配置的跨域不生效问题(主要由于security的过滤器优先级高于springboot的优先级)
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}