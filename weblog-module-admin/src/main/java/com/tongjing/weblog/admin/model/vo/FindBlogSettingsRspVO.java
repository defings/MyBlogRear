package com.tongjing.weblog.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/10 16:06]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/10 16:06]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindBlogSettingsRspVO {

    private String logo;

    private String name;

    private String author;
    private String menuColor;
    private String backgroundImage;

    private String introduction;

    private String avatar;

    private String githubHomepage;

    private String csdnHomepage;

    private String giteeHomepage;

    private String zhihuHomepage;
}