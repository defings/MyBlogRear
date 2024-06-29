package com.tongjing.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongjing.weblog.admin.model.vo.FindBlogSettingsRspVO;
import com.tongjing.weblog.admin.model.vo.UpdateBlogSettingsReqVO;
import com.tongjing.weblog.admin.service.AdminBlogSettingsService;
import com.tongjing.weblog.common.domain.dos.BlogSettingsDO;
import com.tongjing.weblog.common.domain.mapper.BlogSettingsMapper;
import com.tongjing.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/9 20:38]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/9 20:38]
 * @updateRemark : [说明本次修改内容]
 */
@Service
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {
    private BlogSettingsMapper blogSettingsMapper;
    @Autowired
    public void setBlogSettingsMapper(BlogSettingsMapper blogSettingsMapper) {
        this.blogSettingsMapper = blogSettingsMapper;
    }

    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        // VO 转 DO
        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
                .id(1L)
                .logo(updateBlogSettingsReqVO.getLogo())
                .name(updateBlogSettingsReqVO.getName())
                .author(updateBlogSettingsReqVO.getAuthor())
                .introduction(updateBlogSettingsReqVO.getIntroduction())
                .notesDescription(updateBlogSettingsReqVO.getNotesDescription())
                .avatar(updateBlogSettingsReqVO.getAvatar())
                .backgroundImage(updateBlogSettingsReqVO.getBackgroundImage())
                .menuColor(updateBlogSettingsReqVO.getMenuColor())
                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
                .qq(updateBlogSettingsReqVO.getQq())
                .weixing(updateBlogSettingsReqVO.getWeixing())
                .email(updateBlogSettingsReqVO.getEmail())
                .caseNumber(updateBlogSettingsReqVO.getCaseNumber())
                .build();

        // 保存或更新（当数据库中存在 ID 为 1 的记录时，则执行更新操作，否则执行插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }
    /**
     * 获取博客设置详情
     *
     * @return
     */
    @Override
    public Response findDetail() {
        // 查询 ID 为 1 的记录
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);

        // DO 转 VO
        FindBlogSettingsRspVO vo = FindBlogSettingsRspVO.builder()
                .logo(blogSettingsDO.getLogo())
                .name(blogSettingsDO.getName())
                .author(blogSettingsDO.getAuthor())
                .menuColor(blogSettingsDO.getMenuColor())
                .backgroundImage(blogSettingsDO.getBackgroundImage())
                .introduction(blogSettingsDO.getIntroduction())
                .notesDescription(blogSettingsDO.getNotesDescription())
                .avatar(blogSettingsDO.getAvatar())
                .githubHomepage(blogSettingsDO.getGithubHomepage())
                .qq(blogSettingsDO.getQq())
                .weixing(blogSettingsDO.getWeixing())
                .email(blogSettingsDO.getEmail())
                .caseNumber(blogSettingsDO.getCaseNumber())
                .build();

        return Response.success(vo);
    }
}