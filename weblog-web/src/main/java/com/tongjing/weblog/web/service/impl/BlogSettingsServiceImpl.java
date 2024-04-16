package com.tongjing.weblog.web.service.impl;

import com.tongjing.weblog.common.domain.dos.BlogSettingsDO;
import com.tongjing.weblog.common.domain.mapper.BlogSettingsMapper;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindBlogSettingsDetailRspVO;
import com.tongjing.weblog.web.service.BlogSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:39]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:39]
 * @updateRemark : [说明本次修改内容]
 */
@Service
@Slf4j
public class BlogSettingsServiceImpl implements BlogSettingsService {

    private BlogSettingsMapper blogSettingsMapper;

    @Autowired
    public void setBlogSettingsMapper(BlogSettingsMapper blogSettingsMapper) {
        this.blogSettingsMapper = blogSettingsMapper;
    }

    /**
     * 获取博客设置信息
     *
     * @return
     */
    @Override
    public Response findDetail() {
        // 查询博客设置信息（约定的 ID 为 1）
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        return Response.success(FindBlogSettingsDetailRspVO.builder()
                .logo(blogSettingsDO.getLogo())
                .name(blogSettingsDO.getName())
                .author(blogSettingsDO.getAuthor())
                .introduction(blogSettingsDO.getIntroduction())
                .avatar(blogSettingsDO.getAvatar())
                .menuColor(blogSettingsDO.getMenuColor())
                .backgroundImage(blogSettingsDO.getBackgroundImage())
                .qq(blogSettingsDO.getQq())
                .githubHomepage(blogSettingsDO.getGithubHomepage())
                .weixing(blogSettingsDO.getWeixing())
                .email(blogSettingsDO.getEmail())
                .caseNumber(blogSettingsDO.getCaseNumber())
                .build());
    }
}