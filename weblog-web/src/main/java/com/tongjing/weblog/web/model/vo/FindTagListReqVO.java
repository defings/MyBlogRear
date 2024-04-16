package com.tongjing.weblog.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/15 15:10]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/15 15:10]
 * @updateRemark : [说明本次修改内容]
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagListReqVO {
    private Long size;
}
