package com.tongjing.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.tongjing.weblog.common.domain.dos.ArticleDO;
import com.tongjing.weblog.common.domain.mapper.ArticleMapper;
import com.tongjing.weblog.common.utils.PageResponse;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindArchiveArticlePageListReqVO;
import com.tongjing.weblog.web.model.vo.FindArchiveArticlePageListRspVO;
import com.tongjing.weblog.web.model.vo.FindArchiveArticleRspVO;
import com.tongjing.weblog.web.service.ArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/13 17:50]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/13 17:50]
 * @updateRemark : [说明本次修改内容]
 */
@Service
@Slf4j
public class ArchiveServiceImpl implements ArchiveService {

    private ArticleMapper articleMapper;

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 获取文章归档分页数据
     *
     * @param findArchiveArticlePageListReqVO
     * @return
     */
    @Override
    public Response findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        Long current = findArchiveArticlePageListReqVO.getCurrent();
        Long size = findArchiveArticlePageListReqVO.getSize();

        // 分页查询
        IPage<ArticleDO> page = articleMapper.selectPageList(current, size, "", null, null);
        List<ArticleDO> articleDOS = page.getRecords();

        List<FindArchiveArticlePageListRspVO> vos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(articleDOS)) {
            // DO 转 VO
            List<FindArchiveArticleRspVO> archiveArticleRspVOS = articleDOS.stream()
                    .map(articleDO -> FindArchiveArticleRspVO.builder()
                            .id(articleDO.getId())
                            .cover(articleDO.getCover())
                            .title(articleDO.getTitle())
                            .summary(articleDO.getSummary())
                            .createDate(articleDO.getCreateTime())
                            .createMonth(YearMonth.from(articleDO.getCreateTime()))
                            .build())
                    .collect(Collectors.toList());

            // 按创建的月份进行分组
            Map<YearMonth, List<FindArchiveArticleRspVO>> map = archiveArticleRspVOS.stream().collect(Collectors.groupingBy(FindArchiveArticleRspVO::getCreateMonth));
            // 使用 TreeMap 按月份倒序排列
            Map<YearMonth, List<FindArchiveArticleRspVO>> sortedMap = new TreeMap<>(Collections.reverseOrder());
            sortedMap.putAll(map);

            // 遍历排序后的 Map，将其转换为归档 VO
            sortedMap.forEach((k, v) -> vos.add(FindArchiveArticlePageListRspVO.builder().month(k).articles(v).build()));
        }

        return PageResponse.success(page, vos);
    }
}