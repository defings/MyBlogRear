package com.tongjing.weblog.web.service;

import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindTagArticlePageListReqVO;
import com.tongjing.weblog.web.model.vo.FindTagListReqVO;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:34]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:34]
 * @updateRemark : [说明本次修改内容]
 */
public interface TagService {
    /**
     * 获取标签列表
     * @return
     */
    Response findTagList(FindTagListReqVO findTagListReqVO);

    /**
     * 获取标签下文章分页列表
     * @param findTagArticlePageListReqVO
     * @return
     */
    Response findTagPageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO);
}
