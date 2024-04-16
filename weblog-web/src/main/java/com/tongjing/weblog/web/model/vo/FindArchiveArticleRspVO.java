package com.tongjing.weblog.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/13 17:49]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/13 17:49]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArchiveArticleRspVO {
    private Long id;
    private String cover;
    private String title;
    private String summary;
    /**
     * 发布日期
     */
    private LocalDate createDate;

    /**
     * 发布的月份（此字段不需要展示在前端，主要用于按月份分组使用）
     */
    private YearMonth createMonth;
}