package com.tongjing.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tongjing.weblog.admin.model.vo.*;
import com.tongjing.weblog.admin.service.AdminCategoryService;
import com.tongjing.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.tongjing.weblog.common.domain.dos.CategoryDO;
import com.tongjing.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.tongjing.weblog.common.domain.mapper.CategoryMapper;
import com.tongjing.weblog.common.enums.ResponseCodeEnum;
import com.tongjing.weblog.common.utils.PageResponse;
import com.tongjing.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 15:06]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 15:06]
 * @updateRemark : [说明本次修改内容]
 */
@Service
@Slf4j
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private CategoryMapper categoryMapper;
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    @Autowired
    public void setArticleCategoryRelMapper(ArticleCategoryRelMapper articleCategoryRelMapper) {
        this.articleCategoryRelMapper = articleCategoryRelMapper;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Response addCategory(AddCategoryReqVO addCategoryReqVO) {
        String categoryName = addCategoryReqVO.getName();

        // 先判断该分类是否已经存在
        CategoryDO categoryDO = categoryMapper.selectByName(categoryName);

        if (Objects.nonNull(categoryDO)) {
            log.warn("分类名称： {}, 此分类已存在", categoryName);
            return Response.fail(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }

        // 构建 DO 类
        CategoryDO insertCategoryDO = CategoryDO.builder()
                .name(addCategoryReqVO.getName().trim())
                .build();

        // 执行 insert
        categoryMapper.insert(insertCategoryDO);

        return Response.success();
    }

    @Override
    public PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();

        Page<CategoryDO> page = new Page<>(current, size);
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStartDate();
        LocalDate endDate = findCategoryPageListReqVO.getEndDate();
        wrapper.like(StringUtils.isNotBlank(name), CategoryDO::getName, name.trim())
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)
                .orderByDesc(CategoryDO::getCreateTime);
        Page<CategoryDO> categoryDOPage = categoryMapper.selectPage(page, wrapper);
        List<CategoryDO> categoryDOS = categoryDOPage.getRecords();
        List<FindCategoryPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream()
                    .map(categoryDO -> FindCategoryPageListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .articlesTotal(categoryDO.getArticlesTotal())
                            .createTime(categoryDO.getCreateTime())
                            .build()).collect(Collectors.toList());
        }
        return PageResponse.success(categoryDOPage, vos);
    }

    @Override
    public Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO) {
        // 分类 ID
        Long categoryId = deleteCategoryReqVO.getId();

        // 校验该分类下是否已经有文章，若有，则提示需要先删除分类下所有文章，才能删除
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectOneByCategoryId(categoryId);

        if (Objects.nonNull(articleCategoryRelDO)) {
            log.warn("==> 此分类下包含文章，无法删除，categoryId: {}", categoryId);
            return Response.fail(ResponseCodeEnum.CATEGORY_CAN_NOT_DELETE);
        }

        // 删除分类
        categoryMapper.deleteById(categoryId);

        return Response.success();
    }

    @Override
    public Response findCategorySelectList() {
        // 查询所有分类
        List<CategoryDO> categoryDOS = categoryMapper.selectList(null);

        // DO 转 VO
        List<SelectRspVO> selectRspVOS = null;
        // 如果分类数据不为空
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            // 将分类 ID 作为 Value 值，将分类名称作为 label 展示
            selectRspVOS = categoryDOS.stream()
                    .map(categoryDO -> SelectRspVO.builder()
                            .label(categoryDO.getName())
                            .value(categoryDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(selectRspVOS);
    }
}
