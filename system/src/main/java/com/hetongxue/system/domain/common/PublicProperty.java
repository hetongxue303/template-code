package com.hetongxue.system.domain.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 公共属性
 * @ClassNmae: PublicProperty
 * @Author: 何同学
 * @DateTime: 2022-08-12 13:06
 */
@Data
public class PublicProperty {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}