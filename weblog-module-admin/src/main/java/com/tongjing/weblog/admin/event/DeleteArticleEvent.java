package com.tongjing.weblog.admin.event;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 17:12]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 17:12]
 * @updateRemark : [说明本次修改内容]
 */
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeleteArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public DeleteArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}