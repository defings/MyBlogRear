package com.tongjing.weblog.admin.service;

import com.tongjing.weblog.common.utils.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/9 20:14]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/9 20:14]
 * @updateRemark : [说明本次修改内容]
 */
public interface AdminFileService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    Response uploadFile(MultipartFile file);
}