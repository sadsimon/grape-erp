package net.grape.framework.security.crypto;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 采用国密SM3加密算法，对系统密码进行加密
 *
 * @author 1161095164@qq.com
 *
 */
public class Sm3PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return SmUtil.sm3(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StrUtil.equals(SmUtil.sm3(rawPassword.toString()), encodedPassword);
    }
}
