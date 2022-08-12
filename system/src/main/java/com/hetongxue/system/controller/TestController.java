package com.hetongxue.system.controller;

import com.hetongxue.configuration.redis.utils.RedisUtils;
import com.hetongxue.response.Result;
import com.hetongxue.system.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 测试模块
 * @ClassNmae: TestController
 * @Author: 何同学
 * @DateTime: 2022-08-12 12:33
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/hello")
    public Result Test() {
        return Result.Success().setMessage("hello word!");
    }

    @GetMapping("/datasource")
    public Result TestDatasource() {
        return Result.Success(userService.getUserAll());
    }

    @GetMapping("/redis")
    public Result TestRedis() {
        redisUtils.setValue("test", 123456);
        return Result.Success(redisUtils.getValue("test"));
    }

}