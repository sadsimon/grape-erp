package net.grape.framework.security.mobile;

/**
 * 手机短信登录，验证码效验
 *
 * @author 1161095164@qq.com
 *
 */
public interface MobileVerifyCodeService {

    boolean verifyCode(String mobile, String code);
}
