package com.tongjing.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/9 20:31]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/9 20:31]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_blog_settings")
public class BlogSettingsDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String logo;

    private String name;

    private String author;
    private String menuColor;
    private String backgroundImage;

    private String introduction;

    private String avatar;

    private String githubHomepage;

    private String qq;

    private String weixing;

    private String email;
    private String caseNumber;
}