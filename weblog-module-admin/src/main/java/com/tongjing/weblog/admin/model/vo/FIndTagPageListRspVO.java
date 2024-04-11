package com.tongjing.weblog.admin.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 20:22]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 20:22]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("标签分页查询VO")
public class FIndTagPageListRspVO {
    private long id;
    private String name;
    private LocalDateTime createTime;
}
