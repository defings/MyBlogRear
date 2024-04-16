package com.tongjing.weblog.web.controller;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 16:09]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 16:09]
 * @updateRemark : [说明本次修改内容]
 */

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.SearchArticlePageListReqVO;
import com.tongjing.weblog.web.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "搜索")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/article/search")
    @ApiOperation(value = "文章搜索")
    @ApiOperationLog(description = "文章搜索")
    public Response searchArticlePageList(@RequestBody @Validated SearchArticlePageListReqVO searchArticlePageListReqVO) {
        return searchService.searchArticlePageList(searchArticlePageListReqVO);
    }

}