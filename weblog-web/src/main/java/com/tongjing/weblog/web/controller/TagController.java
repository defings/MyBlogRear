package com.tongjing.weblog.web.controller;

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindTagArticlePageListReqVO;
import com.tongjing.weblog.web.model.vo.FindTagListReqVO;
import com.tongjing.weblog.web.service.TagService;
import com.tongjing.weblog.web.service.impl.TagServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:35]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:35]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/tag")
@Api(tags = "标签")
public class TagController {

    private TagServiceImpl tagService;

    @Autowired
    public void setTagService(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/list")
    @ApiOperation(value = "前台获取标签列表")
    @ApiOperationLog(description = "前台获取标签列表")
    public Response findTagList(@RequestBody FindTagListReqVO findTagListReqVO) {
        return tagService.findTagList(findTagListReqVO);
    }
    @PostMapping("/article/list")
    @ApiOperation(value = "前台获取标签下文章列表")
    @ApiOperationLog(description = "前台获取标签下文章列表")
    public Response findTagPageList(@RequestBody @Validated FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        return tagService.findTagPageList(findTagArticlePageListReqVO);
    }
}