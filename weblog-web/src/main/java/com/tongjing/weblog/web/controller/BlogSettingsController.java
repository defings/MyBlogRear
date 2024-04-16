package com.tongjing.weblog.web.controller;

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.service.BlogSettingsService;
import com.tongjing.weblog.web.service.impl.BlogSettingsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:42]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:42]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/blog/settings")
@Api(tags = "博客设置")
public class BlogSettingsController {

    private BlogSettingsServiceImpl blogSettingsService;

    @Autowired
    public void setBlogSettingsService(BlogSettingsServiceImpl blogSettingsService) {
        this.blogSettingsService = blogSettingsService;
    }

    @PostMapping("/detail")
    @ApiOperation(value = "前台获取博客详情")
    @ApiOperationLog(description = "前台获取博客详情")
    public Response findDetail() {
        return blogSettingsService.findDetail();
    }

}