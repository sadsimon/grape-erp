package net.grape.storage.properties;

import lombok.Data;

/**
 * 腾讯云存储配置项
 *
 * @author 1161095164@qq.com
 *
 */
@Data
public class TencentStorageProperties {
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucketName;
}
