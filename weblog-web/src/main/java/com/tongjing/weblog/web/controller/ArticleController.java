package com.tongjing.weblog.web.controller;

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindArticleDetailReqVO;
import com.tongjing.weblog.web.model.vo.FindIndexArticlePageListReqVO;
import com.tongjing.weblog.web.service.ArticleService;
import com.tongjing.weblog.web.service.impl.ArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:30]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:30]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@Api(tags = "文章")
public class ArticleController {
    private ArticleServiceImpl articleService;

    @Autowired
    public void setArticleService(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article/list")
    @ApiOperation(value = "获取首页文章分页数据")
    @ApiOperationLog(description = "获取首页文章分页数据")
    public Response findArticlePageList(@RequestBody FindIndexArticlePageListReqVO findIndexArticlePageListReqVO) {
        return articleService.findArticlePageList(findIndexArticlePageListReqVO);
    }

    @PostMapping("/article/detail")
    @ApiOperation(value = "获取文章详情")
    @ApiOperationLog(description = "获取文章详情")
    public Response findArticleDetail(@RequestBody FindArticleDetailReqVO findArticleDetailReqVO) {
        return articleService.findArticleDetail(findArticleDetailReqVO);
    }

}