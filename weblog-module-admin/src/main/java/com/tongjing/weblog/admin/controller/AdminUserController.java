package com.tongjing.weblog.admin.controller;

import com.tongjing.weblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.tongjing.weblog.admin.service.impl.AdminUserServiceImpl;
import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @createTime : [2024/4/8 12:54]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 12:54]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员接口")
public class AdminUserController {
    private AdminUserServiceImpl adminUserService;
    @Autowired
    public void setAdminUserService(AdminUserServiceImpl adminUserService) {
        this.adminUserService = adminUserService;
    }
    @PostMapping("/password/update")
    @ApiOperation(value = "修改用户密码")
    @ApiOperationLog(description = "修改用户密码")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public Response updatePassword(@RequestBody @Validated UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        return adminUserService.updatePassword(updateAdminUserPasswordReqVO);
    }
    @PostMapping("/user/info")
    @ApiOperation(value = "获取当前用户信息")
    @ApiOperationLog(description = "获取当前用户信息")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public Response findUserInfo() {
        return adminUserService.findUserInfo();
    }
}
