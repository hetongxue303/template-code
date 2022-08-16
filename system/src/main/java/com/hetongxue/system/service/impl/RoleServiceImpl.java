package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.mapper.RoleMapper;
import com.hetongxue.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 角色业务实现
 * @ClassNmae: RoleServiceImpl
 * @Author: 何同学
 * @DateTime: 2022-08-16 21:30
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}