package com.code.demo.tenant.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.code.framework.tenant.core.annotations.TenantIgnore;
import lombok.Data;

@Data
@TableName("sys_menu")
@TenantIgnore
public class Menu {
    @TableField
    private Long id;
    private String name;
}