package com.tongjing.weblog.common.domain.vo;

import com.tongjing.weblog.common.domain.dos.UserRoleDO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/15 16:44]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/15 16:44]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FindUserAllResp {
    private Long id;

    private String username;

    private String avatarPath;

    private LocalDateTime createTime;

    private Boolean isDeleted;

    private String role;
}
