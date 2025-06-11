package net.grape.system.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import net.grape.framework.common.cache.RedisCache;
import net.grape.framework.common.cache.RedisKeys;
import net.grape.system.enums.SysParamsEnum;
import net.grape.system.service.SysCaptchaService;
import net.grape.system.service.SysParamsService;
import net.grape.system.vo.SysCaptchaVO;
import org.springframework.stereotype.Service;

/**
 * 验证码
 *
 * @author 1161095164@qq.com
 *
 */
@Service
@AllArgsConstructor
public class SysCaptchaServiceImpl implements SysCaptchaService {
    private final RedisCache redisCache;
    private final SysParamsService sysParamsService;

    @Override
    public SysCaptchaVO generate() {
        // 生成验证码key
        String key = UUID.randomUUID().toString();

        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        String image = captcha.toBase64();

        // 保存到缓存
        String redisKey = RedisKeys.getCaptchaKey(key);
        redisCache.set(redisKey, captcha.text(), 300);

        // 封装返回数据
        SysCaptchaVO captchaVO = new SysCaptchaVO();
        captchaVO.setKey(key);
        captchaVO.setImage(image);

        return captchaVO;
    }

    @Override
    public boolean validate(String key, String code) {
        // 如果关闭了验证码，则直接效验通过
        if (!isCaptchaEnabled()) {
            return true;
        }

        if (StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
            return false;
        }

        // 获取验证码
        String captcha = getCache(key);

        // 效验成功
        return code.equalsIgnoreCase(captcha);
    }

    @Override
    public boolean isCaptchaEnabled() {
        return sysParamsService.getBoolean(SysParamsEnum.LOGIN_CAPTCHA.name());
    }

    private String getCache(String key) {
        key = RedisKeys.getCaptchaKey(key);
        String captcha = (String) redisCache.get(key);
        // 删除验证码
        if (captcha != null) {
            redisCache.delete(key);
        }

        return captcha;
    }

}
