package com.shui.exam.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用于注入oss相关的数据
 */
@Component
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssKey {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
