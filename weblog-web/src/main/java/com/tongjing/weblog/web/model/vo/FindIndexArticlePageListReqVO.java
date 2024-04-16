package com.tongjing.weblog.web.model.vo;

import com.tongjing.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:18]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:18]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@Builder
@ApiModel(value = "首页查询文章分页 VO")
public class FindIndexArticlePageListReqVO extends BasePageQuery {

}
