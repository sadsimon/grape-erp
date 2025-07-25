package net.grape.security.service;

import lombok.AllArgsConstructor;
import net.grape.api.module.system.SmsApi;
import net.grape.framework.security.mobile.MobileVerifyCodeService;
import org.springframework.stereotype.Service;

/**
 * 短信验证码效验
 *
 * @author 1161095164@qq.com
 *
 */
@Service
@AllArgsConstructor
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {
    private final SmsApi smsApi;

    @Override
    public boolean verifyCode(String mobile, String code) {
        return smsApi.verifyCode(mobile, code);
    }
}
