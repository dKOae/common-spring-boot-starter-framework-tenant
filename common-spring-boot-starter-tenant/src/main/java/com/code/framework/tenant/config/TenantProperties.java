package com.code.framework.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

/**
 * @author zdl
 * @date 2025/12/2
 * @description 租户插件配置类
 */
@Data
@ConfigurationProperties(prefix = "tenant")
public class TenantProperties {
    /**
     * 租户是否开启
     */
    private static final Boolean ENABLE_DEFAULT = true;

    /**
     * 是否开启
     */
    private Boolean enabled = ENABLE_DEFAULT;

    /**
     * 需要忽略的表
     */
    private Set<String> ignoreTables = Collections.emptySet();


}