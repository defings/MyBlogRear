package com.tongjing.weblog.web.model.vo;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 10:27]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 10:27]
 * @updateRemark : [说明本次修改内容]
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArticleDetailRspVO {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章正文（HTML）
     */
    private String content;
    /**
     * 发布时间
     */
    private LocalDate createTime;
    /**
     * 分类 ID
     */
    private Long categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 阅读量
     */
    private Long readNum;
    private String cover;
    /**
     * 总字数
     */
    private Integer totalWords;

    /**
     * 阅读时长
     */
    private String readTime;
    /**
     * 标签集合
     */
    private List<FindTagListRspVO> tags;
    /**
     * 上一篇文章
     */
    private FindPreNextArticleRspVO preArticle;
    /**
     * 下一篇文章
     */
    private FindPreNextArticleRspVO nextArticle;
}