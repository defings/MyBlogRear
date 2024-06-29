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
 * @createTime : [2024/6/18 10:48]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/18 10:48]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_random_notes")
public class RandomNotesDo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String content;
    private String description;
    private int status;
    private LocalDateTime creatTime;
    private LocalDateTime updateTime;
}
