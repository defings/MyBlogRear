package com.tongjing.weblog.web.controller;

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindCategoryArticlePageListReqVO;
import com.tongjing.weblog.web.model.vo.FindCategoryListReqVO;
import com.tongjing.weblog.web.service.CategoryService;
import com.tongjing.weblog.web.service.impl.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:33]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:33]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类")
public class CategoryController {


    private CategoryServiceImpl categoryService;
    @Autowired
    public void setCategoryService(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/list")
    @ApiOperation(value = "前台获取分类列表")
    @ApiOperationLog(description = "前台获取分类列表")
    public Response findCategoryList(@RequestBody @Validated FindCategoryListReqVO findCategoryListReqVO) {
        return categoryService.findCategoryList(findCategoryListReqVO);
    }

    @PostMapping("/article/list")
    @ApiOperation(value = "前台获取分类下文章分页数据")
    @ApiOperationLog(description = "前台获取分类下文章分页数据")
    public Response findCategoryArticlePageList(@RequestBody @Validated FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO) {
        return categoryService.findCategoryArticlePageList(findCategoryArticlePageListReqVO);
    }

}