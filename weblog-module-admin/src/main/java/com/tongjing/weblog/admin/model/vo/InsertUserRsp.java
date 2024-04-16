package com.tongjing.weblog.admin.model.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/15 19:24]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/15 19:24]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InsertUserRsp {
    private String username;
    private String role;
}
