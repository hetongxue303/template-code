package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description: 用户业务
 * @InterfaceNmae: IUserService
 * @Author: 何同学
 * @DateTime: 2022-08-12 13:08
 */
public interface UserService extends IService<User>, UserDetailsService {

}