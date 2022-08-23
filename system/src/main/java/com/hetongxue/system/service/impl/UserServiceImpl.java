package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.configuration.redis.utils.RedisUtils;
import com.hetongxue.lang.Const;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.dto.LoginDto;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户业务实现
 * @ClassNmae: UserServiceImpl
 * @Author: 何同学
 * @DateTime: 2022-08-12 13:08
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public List<User> getUserAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User login(LoginDto login) {
        String value = (String) redisUtils.getValue(Const.CAPTCHA_KEY);
        if (value.equals(login.getCode())) {
            return null;
        }
        return null;
    }

}