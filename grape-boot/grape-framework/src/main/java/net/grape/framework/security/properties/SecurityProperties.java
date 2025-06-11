package net.grape.framework.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 安全配置项
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "grape.security")
public class SecurityProperties {
    /**
     * accessToken 过期时间(单位：秒)，默认2小时
     */
    private int accessTokenExpire = 60 * 60 * 2;
    /**
     * refreshToken 过期时间(单位：秒)，默认14天
     */
    private int refreshTokenExpire = 60 * 60 * 24 * 14;
}
