package com.tongjing.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongjing.weblog.admin.model.vo.*;
import com.tongjing.weblog.admin.service.AdminTagService;
import com.tongjing.weblog.common.domain.dos.ArticleTagRelDO;
import com.tongjing.weblog.common.domain.dos.TagDo;
import com.tongjing.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.tongjing.weblog.common.domain.mapper.TagMapper;
import com.tongjing.weblog.common.enums.ResponseCodeEnum;
import com.tongjing.weblog.common.utils.PageResponse;
import com.tongjing.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 19:59]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 19:59]
 * @updateRemark : [说明本次修改内容]
 */
@Service
@Slf4j
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDo> implements AdminTagService {
    private TagMapper tagMapper;
    private ArticleTagRelMapper articleTagRelMapper;

    @Autowired
    public void setArticleTagRelMapper(ArticleTagRelMapper articleTagRelMapper) {
        this.articleTagRelMapper = articleTagRelMapper;
    }

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public Response addTag(AddTagReqVo addTagReqVo) {
        List<String> nameList = addTagReqVo.getTags();
        List<TagDo> tagDos = new ArrayList<>();
        for (String name : nameList) {
            TagDo tagDo = tagMapper.selectByName(name);
            if (!Objects.isNull(tagDo)) continue;
            TagDo insertTagDo = TagDo.builder()
                    .name(name)
                    .build();
            tagDos.add(insertTagDo);
        }
        return saveBatch(tagDos) ? Response.success() : Response.fail("插入失败");
    }

    @Override
    public PageResponse findTagList(FIndTagPageListReqVo fIndTagPageListReqVo) {
        Long current = fIndTagPageListReqVo.getCurrent();
        Long size = fIndTagPageListReqVo.getSize();
        Page<TagDo> page = new Page<>(current, size);
        LambdaQueryWrapper<TagDo> wrapper = new LambdaQueryWrapper<>();
        String name = fIndTagPageListReqVo.getName();
        LocalDate startDate = fIndTagPageListReqVo.getStartDate();
        LocalDate endDate = fIndTagPageListReqVo.getEndData();
        wrapper.like(StringUtils.isNotBlank(name), TagDo::getName, name.trim())
                .ge(Objects.nonNull(startDate), TagDo::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), TagDo::getCreateTime, endDate)
                .orderByDesc(TagDo::getCreateTime);
        Page<TagDo> tagDoPage = tagMapper.selectPage(page, wrapper);
        List<TagDo> tagDos = tagDoPage.getRecords();
        List<FIndTagPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDos)) {
            vos = tagDos.stream().map(tagDo -> FIndTagPageListRspVO.builder().id(tagDo.getId())
                    .name(tagDo.getName())
                    .articlesTotal(tagDo.getArticlesTotal())
                    .createTime(tagDo.getCreateTime()).build()).collect(Collectors.toList());
        }
        return PageResponse.success(tagDoPage, vos);
    }

    /**
     * 删除标签
     *
     * @param deleteTagReqVO
     * @return
     */
    @Override
    public Response deleteTag(DeleteTagReqVo deleteTagReqVO) {
        // 标签 ID
        Long tagId = deleteTagReqVO.getId();

        // 校验该标签下是否有关联的文章，若有，则不允许删除，提示用户需要先删除标签下的文章
        ArticleTagRelDO articleTagRelDO = articleTagRelMapper.selectOneByTagId(tagId);

        if (Objects.nonNull(articleTagRelDO)) {
            log.warn("==> 此标签下包含文章，无法删除，tagId: {}", tagId);
            return Response.fail(ResponseCodeEnum.TAG_CAN_NOT_DELETE);
        }

        // 根据标签 ID 删除
        int count = tagMapper.deleteById(tagId);

        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.TAG_NOT_EXISTED);
    }

    @Override
    public Response findAllTag() {
        List<TagDo> tagDos = tagMapper.selectList(null);
        List<SelectRspVO> selectRspVOS = null;
        if (!CollectionUtils.isEmpty(tagDos)) {
            selectRspVOS = tagDos.stream().map(tagDo -> SelectRspVO.builder()
                    .label(tagDo.getName())
                    .value(tagDo.getId())
                    .build()).collect(Collectors.toList());
        }
        return Response.success(selectRspVOS);
    }

    /**
     * 根据标签关键词模糊查询
     *
     * @param searchTagsReqVO
     * @return
     */
    @Override
    public Response searchTags(SearchTagsReqVO searchTagsReqVO) {
        String key = searchTagsReqVO.getKey();

        // 执行模糊查询
        List<TagDo> tagDOS = tagMapper.selectByKey(key);

        // do 转 vo
        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }

    @Override
    public Response findTagSelectList() {
        // 查询所有标签, Wrappers.emptyWrapper() 表示查询条件为空
        List<TagDo> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());

        // DO 转 VO
        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }


}
