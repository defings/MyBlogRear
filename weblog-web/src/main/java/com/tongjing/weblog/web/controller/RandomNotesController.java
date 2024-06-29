package com.tongjing.weblog.web.controller;

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.domain.vo.RandomNotesVo;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindRandomNotesPageListReqVo;
import com.tongjing.weblog.web.service.impl.RandomNotesServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/6/18 11:18]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/18 11:18]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@RequestMapping("/randomNotes")
@Api(tags = "随记")

public class RandomNotesController {
    private RandomNotesServiceImpl randomNotesService;
    @Autowired
    public void setRandomNotesService(RandomNotesServiceImpl randomNotesService) {
        this.randomNotesService = randomNotesService;
    }
    @PostMapping("/allNotes")
    @ApiOperation(value = "前台显示随记内容")
    @ApiOperationLog(description = "前台显示随记内容")
    public Response<List<RandomNotesVo>> allNotes(@Validated @RequestBody FindRandomNotesPageListReqVo findRandomNotesPageListReqVo){
        return Response.success(randomNotesService.findPageRandomNotesByStatus(findRandomNotesPageListReqVo));
    }
}
