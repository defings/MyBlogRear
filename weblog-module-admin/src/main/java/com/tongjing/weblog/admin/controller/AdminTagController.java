package com.tongjing.weblog.admin.controller;

import com.tongjing.weblog.admin.model.vo.AddTagReqVo;
import com.tongjing.weblog.admin.model.vo.DeleteTagReqVo;
import com.tongjing.weblog.admin.model.vo.FIndTagPageListReqVo;
import com.tongjing.weblog.admin.model.vo.SearchTagsReqVO;
import com.tongjing.weblog.admin.service.impl.AdminTagServiceImpl;
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
 * @createTime : [2024/4/9 8:16]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/9 8:16]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin标签管理模块")
public class AdminTagController {
    private AdminTagServiceImpl adminTagService;
    @Autowired
    public void setAdminTagService(AdminTagServiceImpl adminTagService) {
        this.adminTagService = adminTagService;
    }
    @PostMapping("/tag/add")
    @ApiOperation(value = "添加标签")
    @ApiOperationLog(description = "添加标签")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public Response addTag(@RequestBody @Validated AddTagReqVo addTagReqVo){
        return adminTagService.addTag(addTagReqVo);
    }

    @PostMapping("/tag/list")
    @ApiOperation("分页获取标签数据")
    @ApiOperationLog(description = "分页获取标签数据")
    @RolesAllowed({"ADMIN", "VISITOR"})
    public PageResponse findTagList(@RequestBody @Validated FIndTagPageListReqVo fIndTagPageListReqVo){
        return adminTagService.findTagList(fIndTagPageListReqVo);
    }


    @PostMapping("/tag/delete")
    @ApiOperation("删除标签")
    @ApiOperationLog(description = "删除标签")
    public Response deleteTag(@RequestBody @Validated DeleteTagReqVo deleteTagReqVo){
        return adminTagService.deleteTag(deleteTagReqVo);
    }
    @PostMapping("/tag/search")
    @ApiOperation(value = "标签模糊查询")
    @ApiOperationLog(description = "标签模糊查询")
    public Response searchTags(@RequestBody @Validated SearchTagsReqVO searchTagsReqVO) {
        return adminTagService.searchTags(searchTagsReqVO);
    }
    @PostMapping("/select/list")
    @ApiOperation(value = "查询标签 Select 列表数据")
    @ApiOperationLog(description = "查询标签 Select 列表数据")
    public Response findTagSelectList() {
        return adminTagService.findTagSelectList();
    }

}
