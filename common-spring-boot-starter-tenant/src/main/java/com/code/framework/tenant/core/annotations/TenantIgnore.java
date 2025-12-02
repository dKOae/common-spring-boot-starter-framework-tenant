package com.code.framework.tenant.core.annotations;

import java.lang.annotation.*;

/**
 * 标注该注解的实体类对应的表，忽略多租户过滤（通用表专用）
 */
@Target({ElementType.TYPE}) // 仅作用于实体类
@Retention(RetentionPolicy.RUNTIME) // 运行时可解析
@Documented
public @interface TenantIgnore {
}
