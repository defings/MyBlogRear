package com.tongjing.weblog.web.service;

import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindArticleDetailReqVO;
import com.tongjing.weblog.web.model.vo.FindIndexArticlePageListReqVO;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:21]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:21]
 * @updateRemark : [说明本次修改内容]
 */
public interface ArticleService {
    /**
     * 获取首页文章分页数据
     * @param findIndexArticlePageListReqVO
     * @return
     */
    Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO);
    /**
     * 获取文章详情
     * @param findArticleDetailReqVO
     * @return
     */
    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);
}