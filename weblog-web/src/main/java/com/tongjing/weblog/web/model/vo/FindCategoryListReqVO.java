package com.tongjing.weblog.web.model.vo;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/15 14:55]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/15 14:55]
 * @updateRemark : [说明本次修改内容]
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCategoryListReqVO {

    /**
     * 展示数量
     */
    private Long size;

}