package com.tongjing.weblog.web.controller;

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.domain.dos.FriendlyLinkDo;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.service.impl.FriendLinkServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/6/3 15:14]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/3 15:14]
 * @updateRemark : [说明本次修改内容]
 */
@Api(tags = "友情链接")
@RestController
@RequestMapping("/friendly")
public class FriendlyLinkController {
    private FriendLinkServiceImpl friendLinkService;
    @Autowired
    public void setFriendLinkService(FriendLinkServiceImpl friendLinkService) {
        this.friendLinkService = friendLinkService;
    }
    @GetMapping("/findAll")
    @ApiOperation(value = "前台友情链接")
    @ApiOperationLog(description = "前台友情链接")
    public Response<HashMap<String, List<FriendlyLinkDo>>> findAll(){
        return Response.success(friendLinkService.findAll());
    }

}
