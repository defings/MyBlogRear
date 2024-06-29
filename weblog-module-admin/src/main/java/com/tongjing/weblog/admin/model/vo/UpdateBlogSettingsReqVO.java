package com.tongjing.weblog.admin.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/9 20:36]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/9 20:36]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = " 博客基础信息修改 VO")
public class UpdateBlogSettingsReqVO {

    @NotBlank(message = "博客 LOGO 不能为空")
    private String logo;

    @NotBlank(message = "博客名称不能为空")
    private String name;

    @NotBlank(message = "博客作者不能为空")
    private String author;

    @NotBlank(message = "博客介绍语不能为空")
    private String introduction;
    @NotBlank(message = "随记介绍语不能为空")
    private String notesDescription;

    @NotBlank(message = "博客头像不能为空")
    private String avatar;

    @NotBlank(message = "博客背景不能为空")
    private String backgroundImage;
    @NotBlank(message = "博客导航栏不能为空")
    private String menuColor;

    private String githubHomepage;

    private String qq;

    private String weixing;

    private String email;
    private String caseNumber;
}