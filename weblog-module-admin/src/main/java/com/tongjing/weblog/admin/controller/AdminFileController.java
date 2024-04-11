package com.tongjing.weblog.admin.controller;

import com.tongjing.weblog.admin.service.AdminFileService;
import com.tongjing.weblog.admin.service.impl.AdminFileServiceImpl;
import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/9 20:18]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/9 20:18]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin文件模块")
public class AdminFileController {
    private AdminFileServiceImpl adminFileService;
    @Autowired
    public void setAdminFileService(AdminFileServiceImpl adminFileService) {
        this.adminFileService = adminFileService;
    }
    @PostMapping("/file/upload")
    @ApiOperation(value = "文件上传")
    @ApiOperationLog(description = "文件上传")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response uploadFile(@RequestParam MultipartFile file) {
        return adminFileService.uploadFile(file);
    }

}
