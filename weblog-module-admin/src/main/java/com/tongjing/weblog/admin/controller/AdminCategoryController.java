package com.tongjing.weblog.admin.controller;

import com.tongjing.weblog.admin.model.vo.AddCategoryReqVO;
import com.tongjing.weblog.admin.model.vo.DeleteCategoryReqVO;
import com.tongjing.weblog.admin.model.vo.FindCategoryPageListReqVO;
import com.tongjing.weblog.admin.service.impl.AdminCategoryServiceImpl;
import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.PageResponse;
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
 * @createTime : [2024/4/8 15:11]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 15:11]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin文章分类模块")
public class AdminCategoryController {
    private AdminCategoryServiceImpl adminCategoryService;

    @Autowired
    public void setAdminCategoryService(AdminCategoryServiceImpl adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }
    @PostMapping("/category/add")
    @ApiOperation(value = "添加分类")
    @ApiOperationLog(description = "添加分类")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
        return adminCategoryService.addCategory(addCategoryReqVO);
    }
    @PostMapping("/category/list")
    @ApiOperation(value = "分类分页数据获取")
    @ApiOperationLog(description = "分类分页数据获取")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public PageResponse findCategoryList(@RequestBody @Validated FindCategoryPageListReqVO findCategoryPageListReqVO) {
        return adminCategoryService.findCategoryList(findCategoryPageListReqVO);
    }
    @PostMapping("/category/delete")
    @ApiOperation(value = "删除分类")
    @ApiOperationLog(description = "删除分类")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteCategory(@RequestBody @Validated DeleteCategoryReqVO deleteCategoryReqVO) {
        return adminCategoryService.deleteCategory(deleteCategoryReqVO);
    }
    @PostMapping("/category/select/list")
    @ApiOperation(value = "分类 Select 下拉列表数据获取")
    @ApiOperationLog(description = "分类 Select 下拉列表数据获取")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public Response findCategorySelectList() {
        return adminCategoryService.findCategorySelectList();
    }
}
