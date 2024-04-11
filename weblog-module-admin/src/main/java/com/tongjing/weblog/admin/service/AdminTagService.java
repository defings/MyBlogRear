package com.tongjing.weblog.admin.service;

import com.tongjing.weblog.admin.model.vo.AddTagReqVo;
import com.tongjing.weblog.admin.model.vo.DeleteTagReqVo;
import com.tongjing.weblog.admin.model.vo.FIndTagPageListReqVo;
import com.tongjing.weblog.admin.model.vo.SearchTagsReqVO;
import com.tongjing.weblog.common.utils.Response;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 19:38]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 19:38]
 * @updateRemark : [说明本次修改内容]
 */
public interface AdminTagService {
    Response addTag(AddTagReqVo addTagReqVo);
    Response findTagList(FIndTagPageListReqVo fIndTagPageListReqVo);
    Response deleteTag(DeleteTagReqVo deleteTagReqVo);
    Response findAllTag();

    /**
     * 查询标签 Select 列表数据
     * @return
     */
    Response findTagSelectList();

    /**
     * 根据标签关键词模糊查询
     * @param searchTagsReqVO
     * @return
     */
    Response searchTags(SearchTagsReqVO searchTagsReqVO);


}
