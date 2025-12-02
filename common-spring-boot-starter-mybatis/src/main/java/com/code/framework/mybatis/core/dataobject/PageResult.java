package com.code.framework.mybatis.core.dataobject;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zdl
 * @date 2025/12/1
 * @description
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<T> rows;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageResult(List<T> list, long total) {
        this.rows = list;
        this.total = total;
        this.code = HttpStatus.HTTP_OK;
        this.msg = "查询成功";
    }

    /**
     * 根据分页对象构建表格分页数据对象
     */
    public static <T> PageResult<T> build(IPage<T> page) {
        return build(page.getRecords());
    }

    /**
     * 根据数据列表构建表格分页数据对象
     */
    public static <T> PageResult<T> build(List<T> list) {
        return new PageResult<>(list, list.size());
    }

    /**
     * 构建表格分页数据对象
     */
    public static <T> PageResult<T> build() {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        return rspData;
    }

    /**
     * 根据原始数据列表和分页参数，构建表格分页数据对象（用于假分页）
     *
     * @param list 原始数据列表（全部数据）
     * @param page 分页参数对象（包含当前页码、每页大小等）
     * @return 构造好的分页结果 PageResult<T>
     */
    public static <T> PageResult<T> build(List<T> list, IPage<T> page) {
        if (CollUtil.isEmpty(list)) {
            return PageResult.build();
        }
        List<T> pageList = CollUtil.page((int) page.getCurrent() - 1, (int) page.getSize(), list);
        return new PageResult<>(pageList, list.size());
    }
}