package net.grape.email.config;

import lombok.Data;

/**
 * 邮箱配置项
 *
 * @author 1161095164@qq.com
 *
 */
@Data
public class EmailConfig {
    /**
     * 平台ID
     */
    private Long id;

    /**
     * 平台类型
     */
    private Integer platform;

    /**
     * 分组名称，发送邮件时，可指定分组
     */
    private String groupName;

    /**
     * SMTP服务器
     */
    private String mailHost;

    /**
     * SMTP端口
     */
    private Integer mailPort;

    /**
     * 发件人邮箱
     */
    private String mailFrom;

    /**
     * 发件人密码
     */
    private String mailPass;

    /**
     * regionId
     */
    private String regionId;

    /**
     * 阿里云 endpoint
     */
    private String endpoint;

    /**
     * AccessKey
     */
    private String accessKey;

    /**
     * SecretKey
     */
    private String secretKey;

}
