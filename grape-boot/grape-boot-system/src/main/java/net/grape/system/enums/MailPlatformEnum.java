package net.grape.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 邮件平台枚举
 *
 * @author 1161095164@qq.com
 *  
 */
@Getter
@AllArgsConstructor
public enum MailPlatformEnum {
    /**
     * 本地
     */
    LOCAL(-1),
    /**
     * 阿里云
     */
    ALIYUN(0);

    private final int value;

}
