package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hetongxue.system.domain.common.PublicProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 用户实体
 * @ClassNmae: User
 * @Author: 何同学
 * @DateTime: 2022-08-12 13:05
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User extends PublicProperty implements Serializable {

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户性别(0:男(默认) 1:女 2:保密)
     */
    private String gender;
    /**
     * 用户简介
     */
    private String Introduction;
    /**
     * 用户头像地址
     */
    private String avatarPath;
    /**
     * 账户状态(0:不可用 1:可用(默认))
     */
    private boolean status;

}