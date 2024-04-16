package com.tongjing.weblog.admin.model.vo;

import com.tongjing.weblog.common.domain.dos.UserRoleDO;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/15 17:02]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/15 17:02]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpDataUserInfo {
    private long id;
    private String name;
    private String username;
    private String avatarPath;
    private String role;
}
