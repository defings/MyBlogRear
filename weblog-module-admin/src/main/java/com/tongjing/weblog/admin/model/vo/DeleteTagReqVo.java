package com.tongjing.weblog.admin.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 19:56]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 19:56]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("标签删除VO")
public class DeleteTagReqVo {
    @NotNull(message = "id不能为空")
    private long id;
}
