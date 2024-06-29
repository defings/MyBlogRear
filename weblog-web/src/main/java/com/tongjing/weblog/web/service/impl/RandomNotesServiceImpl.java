package com.tongjing.weblog.web.service.impl;

import com.tongjing.weblog.common.domain.dos.RandomNotesDo;
import com.tongjing.weblog.common.domain.mapper.RandomNotesMapper;
import com.tongjing.weblog.common.domain.vo.RandomNotesVo;
import com.tongjing.weblog.web.markdown.MarkdownHelper;
import com.tongjing.weblog.web.model.vo.FindRandomNotesPageListReqVo;
import com.tongjing.weblog.web.service.RandomNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/6/18 11:02]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/18 11:02]
 * @updateRemark : [说明本次修改内容]
 */
@Service
public class RandomNotesServiceImpl implements RandomNotesService {
    private RandomNotesMapper randomNotesMapper;

    @Autowired
    public void setRandomNotesMapper(RandomNotesMapper randomNotesMapper) {
        this.randomNotesMapper = randomNotesMapper;
    }

    @Override
    public List<RandomNotesVo> findAllRandomNotes() {
        return randomNotesMapper.findAllRandomNotes();
    }

    @Override
    public List<RandomNotesVo> findAllRandomNotesByStatus(int status) {
        return randomNotesMapper.findAllRandomNotesByStatus(status);
    }

    @Override
    public List<RandomNotesVo> findPageRandomNotesByStatus(FindRandomNotesPageListReqVo findRandomNotesPageListReqVo) {
        Long current = findRandomNotesPageListReqVo.getCurrent()*findRandomNotesPageListReqVo.getSize();
        List<RandomNotesVo> randomNotesVoList = randomNotesMapper.findPageRandomNotesByStatus(1, current, findRandomNotesPageListReqVo.getSize());
        randomNotesVoList.forEach(
                randomNotesVo -> MarkdownHelper.convertMarkdown2Html(randomNotesVo.getContent())
        );
        return randomNotesVoList;
    }
}
