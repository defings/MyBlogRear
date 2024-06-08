package com.tongjing.weblog.web.service;

import com.tongjing.weblog.common.domain.dos.FriendlyLinkDo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/6/3 14:52]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/3 14:52]
 * @updateRemark : [说明本次修改内容]
 */
public interface FriendlyLinkService {
    public HashMap<String, List<FriendlyLinkDo>> findAll();
}
