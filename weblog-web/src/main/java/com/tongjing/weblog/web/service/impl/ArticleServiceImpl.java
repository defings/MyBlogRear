package com.tongjing.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.tongjing.weblog.admin.event.ReadArticleEvent;
import com.tongjing.weblog.common.domain.dos.*;
import com.tongjing.weblog.common.domain.mapper.*;
import com.tongjing.weblog.common.enums.ResponseCodeEnum;
import com.tongjing.weblog.common.utils.PageResponse;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.markdown.MarkdownHelper;
import com.tongjing.weblog.web.model.vo.*;
import com.tongjing.weblog.web.service.ArticleService;
import com.tongjing.weblog.web.util.MarkdownStatsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:21]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:21]
 * @updateRemark : [说明本次修改内容]
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private ArticleMapper articleMapper;
    private CategoryMapper categoryMapper;
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    private TagMapper tagMapper;
    private ArticleTagRelMapper articleTagRelMapper;
    private ArticleContentMapper articleContentMapper;
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Autowired
    public void setArticleCategoryRelMapper(ArticleCategoryRelMapper articleCategoryRelMapper) {
        this.articleCategoryRelMapper = articleCategoryRelMapper;
    }

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Autowired
    public void setArticleTagRelMapper(ArticleTagRelMapper articleTagRelMapper) {
        this.articleTagRelMapper = articleTagRelMapper;
    }

    @Autowired
    public void setArticleContentMapper(ArticleContentMapper articleContentMapper) {
        this.articleContentMapper = articleContentMapper;
    }

    /**
     * 获取首页文章分页数据
     *
     * @param findIndexArticlePageListReqVO
     * @return
     */
    @Override
    public Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO) {
        Long current = findIndexArticlePageListReqVO.getCurrent();
        Long size = findIndexArticlePageListReqVO.getSize();

        // 第一步：分页查询文章主体记录
        Page<ArticleDO> articleDOPage = articleMapper.selectPageList(current, size, "", null, null);

        // 返回的分页数据
        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        List<FindIndexArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            // 文章 DO 转 VO
            vos = articleDOS.stream()
                    .map(articleDO -> FindIndexArticlePageListRspVO.builder()
                            .id(articleDO.getId())
                            .cover(articleDO.getCover())
                            .title(articleDO.getTitle())
                            .readNum(articleDO.getReadNum())
                            .createTime(articleDO.getCreateTime())
                            .summary(articleDO.getSummary())
                            .build())
                    .collect(Collectors.toList());

            // 拿到所有文章的 ID 集合
            List<Long> articleIds = articleDOS.stream().map(ArticleDO::getId).collect(Collectors.toList());

            // 第二步：设置文章所属分类
            // 查询所有分类
            List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());
            // 转 Map, 方便后续根据分类 ID 拿到对应的分类名称
            Map<Long, String> categoryIdNameMap = categoryDOS.stream().collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));

            // 根据文章 ID 批量查询所有关联记录
            List<ArticleCategoryRelDO> articleCategoryRelDOS = articleCategoryRelMapper.selectByArticleIds(articleIds);

            vos.forEach(vo -> {
                Long currArticleId = vo.getId();
                // 过滤出当前文章对应的关联数据
                Optional<ArticleCategoryRelDO> optional = articleCategoryRelDOS.stream().filter(rel -> Objects.equals(rel.getArticleId(), currArticleId)).findAny();

                // 若不为空
                if (optional.isPresent()) {
                    ArticleCategoryRelDO articleCategoryRelDO = optional.get();
                    Long categoryId = articleCategoryRelDO.getCategoryId();
                    // 通过分类 ID 从 map 中拿到对应的分类名称
                    String categoryName = categoryIdNameMap.get(categoryId);

                    FindCategoryListRspVO findCategoryListRspVO = FindCategoryListRspVO.builder()
                            .id(categoryId)
                            .name(categoryName)
                            .build();
                    // 设置到当前 vo 类中
                    vo.setCategory(findCategoryListRspVO);
                }
            });

            // 第三步：设置文章标签
            // 查询所有标签
            List<TagDo> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());
            // 转 Map, 方便后续根据标签 ID 拿到对应的标签名称
            Map<Long, String> mapIdNameMap = tagDOS.stream().collect(Collectors.toMap(TagDo::getId, TagDo::getName));

            // 拿到所有文章的标签关联记录
            List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByArticleIds(articleIds);
            vos.forEach(vo -> {
                Long currArticleId = vo.getId();
                // 过滤出当前文章的标签关联记录
                List<ArticleTagRelDO> articleTagRelDOList = articleTagRelDOS.stream().filter(rel -> Objects.equals(rel.getArticleId(), currArticleId)).collect(Collectors.toList());

                List<FindTagListRspVO> findTagListRspVOS = Lists.newArrayList();
                // 将关联记录 DO 转 VO, 并设置对应的标签名称
                articleTagRelDOList.forEach(articleTagRelDO -> {
                    Long tagId = articleTagRelDO.getTagId();
                    String tagName = mapIdNameMap.get(tagId);

                    FindTagListRspVO findTagListRspVO = FindTagListRspVO.builder()
                            .id(tagId)
                            .name(tagName)
                            .build();
                    findTagListRspVOS.add(findTagListRspVO);
                });
                // 设置转换后的标签数据
                vo.setTags(findTagListRspVOS);
            });
        }

        return PageResponse.success(articleDOPage, vos);
    }

    /**
     * 获取文章详情
     *
     * @param findArticleDetailReqVO
     * @return
     */
    @Override
    public Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO) {
        Long articleId = findArticleDetailReqVO.getArticleId();

        ArticleDO articleDO = articleMapper.selectById(articleId);
        // 判断文章是否存在
        if (Objects.isNull(articleDO)) {
            log.warn("==> 该文章不存在, articleId: {}", articleId);
            return Response.fail(ResponseCodeEnum.ARTICLE_NOT_FOUND);
        }

        // 查询正文
        ArticleContentDO articleContentDO = articleContentMapper.selectByArticleId(articleId);
        String content = articleContentDO.getContent();

        // 计算 md 正文字数
        Integer totalWords = MarkdownStatsUtil.calculateWordCount(content);

        // DO 转 VO
        FindArticleDetailRspVO vo = FindArticleDetailRspVO.builder()
                .title(articleDO.getTitle())
                .cover(articleDO.getCover())
                .createTime(articleDO.getCreateTime())
                .content(MarkdownHelper.convertMarkdown2Html(articleContentDO.getContent()))
                .readNum(articleDO.getReadNum())
                .totalWords(totalWords)
                .readTime(MarkdownStatsUtil.calculateReadingTime(totalWords))
                .build();

        // 查询所属分类
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectByArticleId(articleId);
        CategoryDO categoryDO = categoryMapper.selectById(articleCategoryRelDO.getCategoryId());
        vo.setCategoryId(categoryDO.getId());
        vo.setCategoryName(categoryDO.getName());

        // 查询标签
        List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByArticleId(articleId);
        List<Long> tagIds = articleTagRelDOS.stream().map(ArticleTagRelDO::getTagId).collect(Collectors.toList());
        List<TagDo> tagDOS = tagMapper.selectByIds(tagIds);

        // 标签 DO 转 VO
        List<FindTagListRspVO> tagVOS = tagDOS.stream()
                .map(tagDO -> FindTagListRspVO.builder().id(tagDO.getId()).name(tagDO.getName()).build())
                .collect(Collectors.toList());
        vo.setTags(tagVOS);

        // 上一篇
        ArticleDO preArticleDO = articleMapper.selectPreArticle(articleId);
        if (Objects.nonNull(preArticleDO)) {
            FindPreNextArticleRspVO preArticleVO = FindPreNextArticleRspVO.builder()
                    .articleId(preArticleDO.getId())
                    .articleTitle(preArticleDO.getTitle())
                    .build();
            vo.setPreArticle(preArticleVO);
        }

        // 下一篇
        ArticleDO nextArticleDO = articleMapper.selectNextArticle(articleId);
        if (Objects.nonNull(nextArticleDO)) {
            FindPreNextArticleRspVO nextArticleVO = FindPreNextArticleRspVO.builder()
                    .articleId(nextArticleDO.getId())
                    .articleTitle(nextArticleDO.getTitle())
                    .build();
            vo.setNextArticle(nextArticleVO);
        }

        // 发布文章阅读事件
        eventPublisher.publishEvent(new ReadArticleEvent(this, articleId));
        return Response.success(vo);
    }
}
