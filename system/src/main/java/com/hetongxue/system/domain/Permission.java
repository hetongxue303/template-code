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
 * @Description: 权限实体
 * @ClassNmae: Permission
 * @Author: 何同学
 * @DateTime: 2022-08-16 21:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission extends PublicProperty implements Serializable {

    /**
     * 权限ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 父ID[默认0]
     */
    private Long parentId;
    /**
     * 类型(0:菜单[默认] 1:菜单项 2:按钮)
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限码
     */
    private String permissionCode;
    /**
     * 路由名称
     */
    private String name;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 是否缓存
     */
    private Boolean cache;
    /**
     * 组件地址
     */
    private String components;

}