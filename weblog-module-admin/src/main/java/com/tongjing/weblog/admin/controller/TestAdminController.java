package com.tongjing.weblog.admin.controller;

import com.tongjing.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/7 18:58]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/7 18:58]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/admin")
@Api("管理员")
public class TestAdminController {
    @ApiOperation("管理员测试接口")
    @RequestMapping("/test")
    public Response<String> test(){
        return Response.success("success");
    }
}
