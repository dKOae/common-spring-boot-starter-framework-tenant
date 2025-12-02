package com.code.demo.tenant.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class User {
    @TableField
    private Long id;
    private String username;
    private String password;
    private Long tenantId;
}