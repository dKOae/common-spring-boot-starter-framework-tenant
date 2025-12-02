package com.code.framework.tenant.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 租户上下文持有者
 * @author zdl
 * @date 2025/12/2
 * @description
 */
public class TenantContextHolder {

    /**
     * 租户id线程本地变量
     * 为什么要使用 TransmittableThreadLocal :
     * 因为在异步线程中,如果不使用 TransmittableThreadLocal, 则在异步线程中获取的租户id为null
     */
    private static final ThreadLocal<Long> TENANT_ID_HOLDER = new TransmittableThreadLocal<>();

    /**
     * 获取租户id
     * @return 租户id
     */
     public static Long getTenantId() {
        return TENANT_ID_HOLDER.get();
    }

    /**
     * 获取必须的租户id
     * @return 租户id
     */
    public static Long getRequiredTenantId() {
        Long tenantId = getTenantId();
        if (tenantId == null) {
            throw new NullPointerException("TenantContextHolder 不存在租户编号！");
        }
        return tenantId;
    }

    /**
     * 设置租户id
     * @param tenantId 租户id
     */
    public static void setTenantId(Long tenantId) {
        TENANT_ID_HOLDER.set(tenantId);
    }

     /**
      * 清除租户id
      */
     public static void clear() {
        TENANT_ID_HOLDER.remove();
    }
}