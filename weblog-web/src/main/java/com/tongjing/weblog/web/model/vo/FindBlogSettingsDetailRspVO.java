package com.tongjing.weblog.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:37]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:37]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindBlogSettingsDetailRspVO {
    private String logo;
    private String name;
    private String author;
    private String introduction;
    private String avatar;
    private String menuColor;
    private String backgroundImage;
    private String githubHomepage;
    private String qq;
    private String weixing;
    private String email;
    private String caseNumber;
}
