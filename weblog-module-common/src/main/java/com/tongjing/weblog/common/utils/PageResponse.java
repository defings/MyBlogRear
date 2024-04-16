package com.tongjing.weblog.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 15:18]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 15:18]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class PageResponse<T> extends Response<List<T>> {
    /**
     * 总记录数
     */
    private long total = 0L;

    /**
     * 每页显示的记录数，默认每页显示 10 条
     */
    private long size = 10L;

    /**
     * 当前页码
     */
    private long current;

    /**
     * 总页数
     */
    private long pages;
    /**
     * 成功响应
     * @param page Mybatis Plus 提供的分页接口
     * @param data
     * @return
     * @param <T>
     */
    public static <T> PageResponse<T> success(IPage page, List<T> data) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setCurrent(Objects.isNull(page) ? 1L : page.getCurrent());
        response.setSize(Objects.isNull(page) ? 10L : page.getSize());
        response.setPages(Objects.isNull(page) ? 0L : page.getPages());
        response.setTotal(Objects.isNull(page) ? 0L : page.getTotal());
        response.setData(data);
        return response;
    }
    public static <T> PageResponse<T> success(long total, long current, long size, List<T> data) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setCurrent(current);
        response.setSize(size);
        // 计算总页数
        int pages = (int) Math.ceil((double) total / size);
        response.setPages(pages);
        response.setTotal(total);
        response.setData(data);
        return response;
    }
}
