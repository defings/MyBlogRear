package com.tongjing.weblog.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/6/18 11:23]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/18 11:23]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RandomNotesVo {
    private Long id;
    private Long userId;
    private String content;
    private String description;
    private String avatarPath;
    private String username;
    private int status;
    private LocalDateTime creatTime;
    private LocalDateTime updateTime;
}
