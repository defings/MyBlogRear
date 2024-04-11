package com.tongjing.weblog.admin.model.vo;

import com.tongjing.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 19:53]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 19:53]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询标签数据入参VO")
public class FIndTagPageListReqVo extends BasePageQuery {
    private String name;
    private LocalDate startDate;
    private LocalDate endData;
}
