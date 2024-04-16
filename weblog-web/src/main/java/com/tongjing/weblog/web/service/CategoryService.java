package com.tongjing.weblog.web.service;

import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindCategoryArticlePageListReqVO;
import com.tongjing.weblog.web.model.vo.FindCategoryListReqVO;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:31]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:31]
 * @updateRemark : [说明本次修改内容]
 */
public interface CategoryService {
    /**
     * 获取分类列表
     * @return
     */
    Response findCategoryList(FindCategoryListReqVO findCategoryListReqVO);
    /**
     * 获取分类下文章分页数据
     * @param findCategoryArticlePageListReqVO
     * @return
     */
    Response findCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO);
}