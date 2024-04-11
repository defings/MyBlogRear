package com.tongjing.weblog.admin.service.impl;

import com.tongjing.weblog.admin.model.vo.UploadFileRspVO;
import com.tongjing.weblog.admin.service.AdminFileService;
import com.tongjing.weblog.admin.util.MinioUtil;
import com.tongjing.weblog.common.enums.ResponseCodeEnum;
import com.tongjing.weblog.common.exception.BizException;
import com.tongjing.weblog.common.utils.Response;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/9 20:15]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/9 20:15]
 * @updateRemark : [说明本次修改内容]
 */
@Service
@Slf4j
public class AdminFileServiceImpl implements AdminFileService {
    private MinioUtil minioUtil;
    @Autowired
    public void setMinioUtil(MinioUtil minioUtil) {
        this.minioUtil = minioUtil;
    }

    @Override
    public Response uploadFile(MultipartFile file) {
        try {
            String url = minioUtil.uploadFile(file);
            return Response.success(UploadFileRspVO.builder().url(url).build());
        } catch (ServerException | InternalException | XmlParserException | InvalidResponseException |
                 InvalidKeyException | NoSuchAlgorithmException | IOException | ErrorResponseException |
                 InsufficientDataException e) {
            log.error("上传出错{}", e.toString());
            return Response.fail(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        }
    }
}
