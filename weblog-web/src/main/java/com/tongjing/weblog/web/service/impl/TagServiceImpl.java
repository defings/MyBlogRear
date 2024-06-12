package com.tongjing.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tongjing.weblog.common.domain.dos.ArticleDO;
import com.tongjing.weblog.common.domain.dos.ArticleTagRelDO;
import com.tongjing.weblog.common.domain.dos.TagDo;
import com.tongjing.weblog.common.domain.mapper.ArticleMapper;
import com.tongjing.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.tongjing.weblog.common.domain.mapper.TagMapper;
import com.tongjing.weblog.common.enums.ResponseCodeEnum;
import com.tongjing.weblog.common.utils.PageResponse;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindTagArticlePageListReqVO;
import com.tongjing.weblog.web.model.vo.FindTagArticlePageListRspVO;
import com.tongjing.weblog.web.model.vo.FindTagListReqVO;
import com.tongjing.weblog.web.model.vo.FindTagListRspVO;
import com.tongjing.weblog.web.service.TagService;
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
 * @createTime : [2024/4/12 10:34]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:34]
 * @updateRemark : [说明本次修改内容]
 */
@Service
@Slf4j
public class TagServiceImpl implements TagService {


    private TagMapper tagMapper;
    private ArticleTagRelMapper articleTagRelMapper;
    private ArticleMapper articleMapper;

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Autowired
    public void setArticleTagRelMapper(ArticleTagRelMapper articleTagRelMapper) {
        this.articleTagRelMapper = articleTagRelMapper;
    }

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 获取标签列表
     *
     * @return
     */
    @Override
    public Response findTagList(FindTagListReqVO findTagListRspVO) {
        Long size = findTagListRspVO.getSize();
        List<TagDo> tagDos = null;
        // 如果接口入参中未指定 size
        if (Objects.isNull(size) || size == 0) {
            // 查询所有分类
            tagDos = tagMapper.selectList(Wrappers.emptyWrapper());
        } else {
            // 否则查询指定的数量
            tagDos = tagMapper.selectByLimit(size);
        }
        // DO 转 VO
        List<FindTagListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDos)) {
            vos = tagDos.stream()
                    .map(tagDO -> FindTagListRspVO.builder()
                            .id(tagDO.getId())
                            .name(tagDO.getName())
                            .articlesTotal(tagDO.getArticlesTotal())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }

    @Override
    public Response findTagPageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        Long current = findTagArticlePageListReqVO.getCurrent();
        Long size = findTagArticlePageListReqVO.getSize();
        // 标签 ID
        Long tagId = findTagArticlePageListReqVO.getId();

        // 判断该标签是否存在
        TagDo tagDO = tagMapper.selectById(tagId);
        if (Objects.isNull(tagDO)) {
            log.warn("==> 该标签不存在, tagId: {}", tagId);
            return Response.fail(ResponseCodeEnum.TAG_NOT_EXISTED);
        }

        // 先查询该标签下所有关联的文章 ID
        List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByTagId(tagId);

        // 若该标签下未发布任何文章
        if (CollectionUtils.isEmpty(articleTagRelDOS)) {
            log.info("==> 该标签下还未发布任何文章, tagId: {}", tagId);
            return PageResponse.success(null, null);
        }

        // 提取所有文章 ID
        List<Long> articleIds = articleTagRelDOS.stream().map(ArticleTagRelDO::getArticleId).collect(Collectors.toList());

        // 根据文章 ID 集合查询文章分页数据
        Page<ArticleDO> page = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOS = page.getRecords();

        // DO 转 VO
        List<FindTagArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            vos = articleDOS.stream()
                    .map(articleDO -> FindTagArticlePageListRspVO.builder()
                            .id(articleDO.getId())
                            .cover(articleDO.getCover())
                            .title(articleDO.getTitle())
                            .createTime(articleDO.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }

        return PageResponse.success(page, vos);
    }
}
