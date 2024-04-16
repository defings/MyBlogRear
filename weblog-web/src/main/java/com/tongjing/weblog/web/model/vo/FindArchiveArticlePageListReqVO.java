package com.tongjing.weblog.web.model.vo;

import com.tongjing.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/13 17:47]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/13 17:47]
 * @updateRemark : [说明本次修改内容]
 */
@Data
@Builder
@ApiModel(value = "文章归档分页 VO")
public class FindArchiveArticlePageListReqVO extends BasePageQuery {
}
