package com.hetongxue.system.controller;

import com.hetongxue.configuration.redis.utils.RedisUtils;
import com.hetongxue.lang.Const;
import com.hetongxue.response.Result;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 安全模块
 * @ClassNmae: AuthController
 * @Author: 何同学
 * @DateTime: 2022-08-12 13:30
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/getCode")
    public Result getCode() {
        // 在java11中使用Nashorn engine  会出现 Warning: Nashorn engine is planned to be removed from a future JDK release
        System.setProperty("nashorn.args", "--no-deprecation-warning");// 解决上诉问题设置
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36, 2);
        redisUtils.setValue(Const.CAPTCHA_KEY, captcha.text(), 60, TimeUnit.SECONDS);// 设置60秒过期
        System.out.println("验证码:" + captcha.text());
        return Result.Success(captcha.toBase64());
    }

}