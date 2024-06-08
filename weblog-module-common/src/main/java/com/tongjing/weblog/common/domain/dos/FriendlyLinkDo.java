package com.tongjing.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/5/16 10:37]
 * @updateUser : [TongJing]
 * @updateTime : [2024/5/16 10:37]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_friendly_link")
public class FriendlyLinkDo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String link;
    private String linkName;
    private String description;
    private String imageLink;
    private String typeName;
    private int isDelete;
    private LocalDateTime createTime;
}
