package com.tongjing.weblog.admin.service;

import com.tongjing.weblog.admin.model.vo.AddCategoryReqVO;
import com.tongjing.weblog.admin.model.vo.DeleteCategoryReqVO;
import com.tongjing.weblog.admin.model.vo.FindCategoryPageListReqVO;
import com.tongjing.weblog.common.utils.PageResponse;
import com.tongjing.weblog.common.utils.Response;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 15:05]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 15:05]
 * @updateRemark : [说明本次修改内容]
 */
public interface AdminCategoryService {
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);
    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);
    /**
     * 删除分类
     * @param deleteCategoryReqVO
     * @return
     */
    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);
    /**
     * 获取文章分类的 Select 列表数据
     * @return
     */
    Response findCategorySelectList();
}
