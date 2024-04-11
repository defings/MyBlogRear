package com.tongjing.weblog.admin.util;

import com.tongjing.weblog.admin.config.MinioConfig;
import com.tongjing.weblog.admin.config.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/9 20:06]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/9 20:06]
 * @updateRemark : [说明本次修改内容]
 */
@Component
@Slf4j
public class MinioUtil {
    private MinioProperties minioProperties;
    private MinioClient minioClient;
    @Autowired
    public void setMinioProperties(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }
    @Autowired
    public void setMinioConfig(MinioClient minioClient) {
        this.minioClient = minioClient;
    }
    public String uploadFile(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (file == null || file.getSize() == 0){
            log.warn("上传文件异常，文件大小不能为空");
            throw new RuntimeException("文件大小不能为空");
        }
        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        String key = UUID.randomUUID().toString().replace("-", "");
        String suffix =  originalFileName.substring(originalFileName.lastIndexOf("."));
        String objectName = String.format("%s%s", key, suffix);
        log.info("开始上传文件至Minio，ObjectName:{}", objectName);
        minioClient.putObject(PutObjectArgs.builder()
                        .bucket(minioProperties.getBucketName())
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(contentType)
                .build());
        String url = String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucketName(), objectName);
        log.info("==> 上传文件至 Minio 成功，访问路径: {}", url);
        return url;
    }
}
