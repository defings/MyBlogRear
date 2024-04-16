package com.tongjing.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tongjing.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.tongjing.weblog.common.domain.dos.ArticleDO;
import com.tongjing.weblog.common.domain.dos.CategoryDO;
import com.tongjing.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.tongjing.weblog.common.domain.mapper.ArticleMapper;
import com.tongjing.weblog.common.domain.mapper.CategoryMapper;
import com.tongjing.weblog.common.enums.ResponseCodeEnum;
import com.tongjing.weblog.common.utils.PageResponse;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.*;
import com.tongjing.weblog.web.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:31]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:31]
 * @updateRemark : [说明本次修改内容]
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {


    private CategoryMapper categoryMapper;
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    private ArticleMapper articleMapper;

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Autowired
    public void setArticleCategoryRelMapper(ArticleCategoryRelMapper articleCategoryRelMapper) {
        this.articleCategoryRelMapper = articleCategoryRelMapper;
    }

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 获取分类列表
     *
     * @return
     */
    /**
     * 获取分类列表
     *
     * @return
     */
    @Override
    public Response findCategoryList(FindCategoryListReqVO findCategoryListReqVO) {
        Long size = findCategoryListReqVO.getSize();

        List<CategoryDO> categoryDOS = null;
        // 如果接口入参中未指定 size
        if (Objects.isNull(size) || size == 0) {
            // 查询所有分类
            categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());
        } else {
            // 否则查询指定的数量
            categoryDOS = categoryMapper.selectByLimit(size);
        }

        // DO 转 VO
        List<FindCategoryListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream()
                    .map(categoryDO -> FindCategoryListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .articlesTotal(categoryDO.getArticlesTotal())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }


    /**
     * 获取分类下文章分页数据
     *
     * @param findCategoryArticlePageListReqVO
     * @return
     */
    @Override
    public Response findCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO) {
        Long current = findCategoryArticlePageListReqVO.getCurrent();
        Long size = findCategoryArticlePageListReqVO.getSize();
        Long categoryId = findCategoryArticlePageListReqVO.getId();

        CategoryDO categoryDO = categoryMapper.selectById(categoryId);

        // 判断该分类是否存在
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 该分类不存在, categoryId: {}", categoryId);
            return Response.fail(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }

        // 先查询该分类下所有关联的文章 ID
        List<ArticleCategoryRelDO> articleCategoryRelDOS = articleCategoryRelMapper.selectListByCategoryId(categoryId);

        // 若该分类下未发布任何文章
        if (CollectionUtils.isEmpty(articleCategoryRelDOS)) {
            log.info("==> 该分类下还未发布任何文章, categoryId: {}", categoryId);
            return PageResponse.success(null, null);
        }

        List<Long> articleIds = articleCategoryRelDOS.stream().map(ArticleCategoryRelDO::getArticleId).collect(Collectors.toList());

        // 根据文章 ID 集合查询文章分页数据
        Page<ArticleDO> page = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOS = page.getRecords();

        // DO 转 VO
        List<FindCategoryArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            vos = articleDOS.stream()
                    .map(articleDO -> FindCategoryArticlePageListRspVO.builder()
                            .id(articleDO.getId())
                            .cover(articleDO.getCover())
                            .title(articleDO.getTitle())
                            .createDate(articleDO.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }

        return PageResponse.success(page, vos);
    }
}