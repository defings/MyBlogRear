package com.tongjing.weblog.admin.model.vo;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/15 19:38]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/15 19:38]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpDataUserStateVo {
    private Long id;
    @Max(value = 1)
    @Min(value = 0)
    private int state;
}
