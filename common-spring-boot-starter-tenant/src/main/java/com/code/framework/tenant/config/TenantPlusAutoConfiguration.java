package com.code.framework.tenant.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.code.framework.mybatis.core.util.MyBatisUtils;
import com.code.framework.tenant.core.filter.TenantContextFilter;
import com.code.framework.tenant.core.handler.CustomTenantLineHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author zdl
 * @date 2025/12/2
 * @description 租户插件自动配置类
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "tenant", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(TenantProperties.class)
public class TenantPlusAutoConfiguration {

    /**
     * 注册租户上下文过滤器
     *
     * @return 租户上下文过滤器
     */
    @Bean
    public TenantContextFilter tenantContextFilter() {
        return new TenantContextFilter();
    }

    @Bean
    public TenantLineInnerInterceptor tenantLineInnerInterceptor(MybatisPlusInterceptor interceptor, TenantProperties properties) {
        TenantLineInnerInterceptor inner = new TenantLineInnerInterceptor();
        inner.setTenantLineHandler(new CustomTenantLineHandler(properties));
        // 需要添加到 MybatisPlusInterceptor 中, 位置为 0，必须要在放在首个
        MyBatisUtils.addInterceptor(interceptor, inner, 0);
        return inner;
    }

}