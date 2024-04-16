package com.tongjing.weblog.web.service.impl;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 15:17]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 15:17]
 * @updateRemark : [说明本次修改内容]
 */

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tongjing.weblog.common.domain.dos.ArticleDO;
import com.tongjing.weblog.common.domain.mapper.ArticleMapper;
import com.tongjing.weblog.common.domain.mapper.CategoryMapper;
import com.tongjing.weblog.common.domain.mapper.TagMapper;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindStatisticsInfoRspVO;
import com.tongjing.weblog.web.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    private ArticleMapper articleMapper;
    private CategoryMapper categoryMapper;
    private TagMapper tagMapper;

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    /**
     * 获取文章总数、分类总数、标签总数、总访问量统计信息
     *
     * @return
     */
    @Override
    public Response findInfo() {
        // 查询文章总数
        Long articleTotalCount = articleMapper.selectCount(Wrappers.emptyWrapper());

        // 查询分类总数
        Long categoryTotalCount = categoryMapper.selectCount(Wrappers.emptyWrapper());

        // 查询标签总数
        Long tagTotalCount = tagMapper.selectCount(Wrappers.emptyWrapper());

        // 总浏览量
        List<ArticleDO> articleDOS = articleMapper.selectAllReadNum();
        Long pvTotalCount = 0L;

        if (!CollectionUtils.isEmpty(articleDOS)) {
            // 所有 read_num 相加
            pvTotalCount = articleDOS.stream().mapToLong(ArticleDO::getReadNum).sum();
        }

        // 组装 VO 类
        FindStatisticsInfoRspVO vo = FindStatisticsInfoRspVO.builder()
                .articleTotalCount(articleTotalCount)
                .categoryTotalCount(categoryTotalCount)
                .tagTotalCount(tagTotalCount)
                .pvTotalCount(pvTotalCount)
                .build();

        return Response.success(vo);
    }
}