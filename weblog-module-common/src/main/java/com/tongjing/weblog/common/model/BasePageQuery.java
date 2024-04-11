package com.tongjing.weblog.common.model;

import lombok.Data;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO 基础分页请求类
 * @createTime : [2024/4/8 15:20]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 15:20]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class BasePageQuery {
    /**
     * 当前页码, 默认第一页
     */
    private Long current = 1L;
    /**
     * 每页展示的数据数量，默认每页展示 10 条数据
     */
    private Long size = 10L;
}