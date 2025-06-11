package net.grape.framework.security.third;

/**
 * 第三方登录，通过code，获取开放平台用户唯一标识
 *
 * @author 1161095164@qq.com
 *  
 */
public interface ThirdOpenIdService {

    /**
     * 通过code，获取开放平台用户唯一标识
     *
     * @param login 第三方登录信息
     * @return 开放平台用户唯一标识
     */
    String getOpenId(ThirdLogin login);
}
