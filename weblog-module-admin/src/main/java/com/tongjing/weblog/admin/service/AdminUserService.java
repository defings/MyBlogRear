package com.tongjing.weblog.admin.service;

import com.tongjing.weblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.tongjing.weblog.common.utils.Response;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 12:50]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 12:50]
 * @updateRemark : [说明本次修改内容]
 */
public interface AdminUserService {
    /**
     * 修改密码
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);

    /**
     * 获取当前登录用户信息
     * @return
     */
    Response findUserInfo();
}
