package com.tongjing.weblog.web.service.impl;

import com.tongjing.weblog.common.domain.dos.FriendlyLinkDo;
import com.tongjing.weblog.common.domain.mapper.FriendlyLinkMapper;
import com.tongjing.weblog.web.service.FriendlyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/6/3 14:54]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/3 14:54]
 * @updateRemark : [说明本次修改内容]
 */
@Service
public class FriendLinkServiceImpl implements FriendlyLinkService {
    private FriendlyLinkMapper friendlyLinkMapper;
    @Autowired
    public void setFriendlyLinkMapper(FriendlyLinkMapper friendlyLinkMapper) {
        this.friendlyLinkMapper = friendlyLinkMapper;
    }

    @Override
    public HashMap<String, List<FriendlyLinkDo>> findAll() {
        List<FriendlyLinkDo> friendlyLinkDoList = friendlyLinkMapper.findAll();
        HashMap<String, List<FriendlyLinkDo>> listHashMap = new HashMap<>();
        friendlyLinkDoList.forEach(friendlyLinkDo ->
                listHashMap.computeIfAbsent(friendlyLinkDo.getTypeName(), k -> new ArrayList<>()).add(friendlyLinkDo)
        );
        return listHashMap;
    }
}
