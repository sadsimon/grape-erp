package net.grape.storage.properties;

import lombok.Data;

/**
 * 阿里云存储配置项
 *
 * @author 1161095164@qq.com
 *  
 */
@Data
public class AliyunStorageProperties {
    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
