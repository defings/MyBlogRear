package com.tongjing.weblog.web.model.vo;

import com.tongjing.weblog.common.model.BasePageQuery;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/6/18 11:32]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/18 11:32]
 * @updateRemark : [说明本次修改内容]
 */

@AllArgsConstructor
@Getter
@Setter
@Builder
public class FindRandomNotesPageListReqVo extends BasePageQuery {
}
