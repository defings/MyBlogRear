package com.tongjing.weblog.admin.controller;

import com.tongjing.weblog.admin.model.vo.*;
import com.tongjing.weblog.admin.service.impl.AdminArticleServiceImpl;
import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/11 11:10]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/11 11:10]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/admin/article")
@Api(tags = "Admin 文章模块")
public class AdminArticleController {

    private AdminArticleServiceImpl articleService;

    @Autowired
    public void setArticleService(AdminArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/publish")
    @ApiOperation(value = "文章发布")
    @ApiOperationLog(description = "文章发布")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public Response publishArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO) {
        return articleService.publishArticle(publishArticleReqVO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "文章删除")
    @ApiOperationLog(description = "文章删除")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteArticle(@RequestBody @Validated DeleteArticleReqVO deleteArticleReqVO) {
        return articleService.deleteArticle(deleteArticleReqVO);
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询文章分页数据")
    @ApiOperationLog(description = "查询文章分页数据")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public Response findArticlePageList(@RequestBody @Validated FindArticlePageListReqVO findArticlePageListReqVO) {
        return articleService.findArticlePageList(findArticlePageListReqVO);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新文章")
    @ApiOperationLog(description = "更新文章")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public Response updateArticle(@RequestBody @Validated UpdateArticleReqVO updateArticleReqVO) {
        return articleService.updateArticle(updateArticleReqVO);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "查询文章详情")
    @ApiOperationLog(description = "查询文章详情")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public Response findArticleDetail(@RequestBody @Validated FindArticleDetailReqVO findArticlePageListReqVO) {
        return articleService.findArticleDetail(findArticlePageListReqVO);
    }

}