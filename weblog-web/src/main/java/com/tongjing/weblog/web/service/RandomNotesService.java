package com.tongjing.weblog.web.service;

import com.tongjing.weblog.common.domain.dos.RandomNotesDo;
import com.tongjing.weblog.common.domain.vo.RandomNotesVo;
import com.tongjing.weblog.web.model.vo.FindRandomNotesPageListReqVo;

import java.util.List;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/6/18 11:01]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/18 11:01]
 * @updateRemark : [说明本次修改内容]
 */
public interface RandomNotesService {
    public List<RandomNotesVo> findAllRandomNotes();
    public List<RandomNotesVo> findAllRandomNotesByStatus(int status);
    public List<RandomNotesVo> findPageRandomNotesByStatus(FindRandomNotesPageListReqVo findRandomNotesPageListReqVo);
}
