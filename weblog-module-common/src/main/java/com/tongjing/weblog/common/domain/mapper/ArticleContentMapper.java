package com.tongjing.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tongjing.weblog.common.domain.dos.ArticleContentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/11 10:56]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/11 10:56]
 * @updateRemark : [说明本次修改内容]
 */
@Mapper
public interface ArticleContentMapper extends BaseMapper<ArticleContentDO> {
    /**
     * 根据文章 ID 删除记录
     * @param articleId
     * @return
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleContentDO>lambdaQuery()
                .eq(ArticleContentDO::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 查询
     * @param articleId
     * @return
     */
    default ArticleContentDO selectByArticleId(Long articleId) {
        return selectOne(Wrappers.<ArticleContentDO>lambdaQuery()
                .eq(ArticleContentDO::getArticleId, articleId));
    }


    /**
     * 通过文章 ID 更新
     * @param articleContentDO
     */
    default int updateByArticleId(ArticleContentDO articleContentDO) {
        return update(articleContentDO,
                Wrappers.<ArticleContentDO>lambdaQuery()
                        .eq(ArticleContentDO::getArticleId, articleContentDO.getArticleId()));
    }
}
