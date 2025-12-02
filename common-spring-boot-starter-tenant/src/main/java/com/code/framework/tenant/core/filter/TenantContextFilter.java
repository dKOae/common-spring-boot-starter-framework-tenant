package com.code.framework.tenant.core.filter;

import cn.hutool.core.util.NumberUtil;
import com.code.framework.tenant.core.context.TenantContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zdl
 * @date 2025/12/2
 * @description 租户上下文过滤器
 */
public class TenantContextFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 从请求头中获取租户id(这个后续可以创建一个常量池来管理, 避免硬编码)
            String tenantIdStr = request.getHeader("tenant-id");
            Long tenantId = NumberUtil.isNumber(tenantIdStr) ? Long.valueOf(tenantIdStr) : null;
            // 设置租户id到上下文
            TenantContextHolder.setTenantId(tenantId);
            // 继续过滤
            filterChain.doFilter(request, response);
        } finally {
            // 清除租户id
            TenantContextHolder.clear();
        }
    }
}