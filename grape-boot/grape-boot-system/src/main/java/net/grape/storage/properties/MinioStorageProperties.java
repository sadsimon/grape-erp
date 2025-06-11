package net.grape.storage.properties;

import lombok.Data;

/**
 * Minio存储配置项
 *
 * @author 1161095164@qq.com
 *  
 */
@Data
public class MinioStorageProperties {
    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
