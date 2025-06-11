package net.grape.storage.properties;

import lombok.Data;

/**
 * 华为云存储配置项
 *
 * @author 1161095164@qq.com
 *  
 */
@Data
public class HuaweiStorageProperties {
    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
