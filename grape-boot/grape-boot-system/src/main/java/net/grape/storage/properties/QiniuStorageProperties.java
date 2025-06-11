package net.grape.storage.properties;

import lombok.Data;

/**
 * 七牛云存储配置项
 *
 * @author 1161095164@qq.com
 *  
 */
@Data
public class QiniuStorageProperties {
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
