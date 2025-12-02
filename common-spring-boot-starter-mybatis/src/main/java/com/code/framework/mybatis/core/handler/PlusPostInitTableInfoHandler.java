package com.code.framework.mybatis.core.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.handlers.PostInitTableInfoHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.session.Configuration;

/**
 * @author zdl
 * @date 2025/12/1
 * @description 初始化表对象处理器,用来控制是否开启逻辑删除
 */
public class PlusPostInitTableInfoHandler implements PostInitTableInfoHandler {

    @Override
    public void postTableInfo(TableInfo tableInfo, Configuration configuration) {
        String flag = SpringUtil.getProperty("mybatis-plus.global-config.db-config.enable-logic-delete", "true");
        boolean enableLogicDelete = Convert.toBool(flag);
        ReflectUtil.setFieldValue(tableInfo, "withLogicDelete", enableLogicDelete);
    }

}