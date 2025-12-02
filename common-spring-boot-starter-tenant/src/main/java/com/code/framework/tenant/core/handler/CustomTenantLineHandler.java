package com.code.framework.tenant.core.handler;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.toolkit.SqlParserUtils;
import com.code.framework.tenant.config.TenantProperties;
import com.code.framework.tenant.core.annotations.TenantIgnore;
import com.code.framework.tenant.core.context.TenantContextHolder;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zdl
 * @date 2025/12/2
 * @description 自定义租户处理器
 */
public class CustomTenantLineHandler implements TenantLineHandler {

    /**
     * 忽略的表
     */
    private final Map<String, Boolean> ignoreTables = new HashMap<>();

    public CustomTenantLineHandler(TenantProperties properties) {
        // 忽略的表默认都为 true
        properties.getIgnoreTables()
                .forEach(tableName -> addIgnoreTable(tableName, Boolean.TRUE));
    }

    /**
     * 自定义租户ID
     *
     * @return 租户ID
     */
    @Override
    public Expression getTenantId() {
        // 从上下文获取租户 id
        return new LongValue(TenantContextHolder.getRequiredTenantId());
    }

    /**
     * 自定义忽略表
     *
     * @param tableName 表名
     * @return 是否忽略
     */
    @Override
    public boolean ignoreTable(String tableName) {
        // 忽略表名时，需要移除表名的包裹符,例如：`tenant_table` -> tenant_table
        tableName = SqlParserUtils.removeWrapperSymbol(tableName);
        Boolean ignore = ignoreTables.get(tableName.toLowerCase());
        if (ignore == null) {
            ignore = computeIgnoreTable(tableName);
        }
        return ignore;
    }

    /**
     * 添加忽略表
     *
     * @param tableName 表名
     * @param ignore    是否忽略 true 忽略 false 不忽略
     */
    private void addIgnoreTable(String tableName, boolean ignore) {
        ignoreTables.put(tableName.toLowerCase(), ignore);
        ignoreTables.put(tableName.toUpperCase(), ignore);
    }

    /**
     * 计算是否忽略表
     *
     * @param tableName 表名
     * @return 是否忽略
     */
    private boolean computeIgnoreTable(String tableName) {
        // 从表信息中获取实体类
        TableInfo tableInfo = TableInfoHelper.getTableInfo(tableName);
        if (tableInfo == null) {
            // 表信息不存在，默认忽略
            return true;
        }
        // 检查实体类是否标注了 @TenantIgnore 注解, 如果标注了, 则忽略多租户过滤
        return tableInfo.getEntityType().getAnnotation(TenantIgnore.class) != null;
    }

}