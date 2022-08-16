package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Permission;
import com.hetongxue.system.mapper.PermissionMapper;
import com.hetongxue.system.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 权限业务实现
 * @ClassNmae: PermissionServiceImpl
 * @Author: 何同学
 * @DateTime: 2022-08-16 21:29
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}