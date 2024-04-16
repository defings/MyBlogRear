package com.tongjing.weblog.web.controller;

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindArchiveArticlePageListReqVO;
import com.tongjing.weblog.web.service.impl.ArchiveServiceImpl;
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
 * @createTime : [2024/4/13 17:59]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/13 17:59]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@Api(tags = "文章归档")
public class ArchiveController {

    private ArchiveServiceImpl archiveService;

    @Autowired
    public void setArchiveService(ArchiveServiceImpl archiveService) {
        this.archiveService = archiveService;
    }

    @PostMapping("/archive/list")
    @ApiOperation(value = "获取文章归档分页数据")
    @ApiOperationLog(description = "获取文章归档分页数据")
    public Response findArchivePageList(@RequestBody FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        return archiveService.findArchivePageList(findArchiveArticlePageListReqVO);
    }

}