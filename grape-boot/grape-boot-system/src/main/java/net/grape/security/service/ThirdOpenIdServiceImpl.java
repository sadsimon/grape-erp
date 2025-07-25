package net.grape.security.service;

import lombok.AllArgsConstructor;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import net.grape.framework.common.exception.ServerException;
import net.grape.framework.security.third.ThirdLogin;
import net.grape.framework.security.third.ThirdOpenIdService;
import net.grape.system.service.SysThirdLoginConfigService;
import org.springframework.stereotype.Service;

/**
 * 第三方登录，通过code，获取开放平台用户唯一标识
 *
 * @author 1161095164@qq.com
 *  
 */
@Service
@AllArgsConstructor
public class ThirdOpenIdServiceImpl implements ThirdOpenIdService {
    private final SysThirdLoginConfigService sysThirdLoginConfigService;

    @Override
    public String getOpenId(ThirdLogin login) {
        AuthRequest authRequest = sysThirdLoginConfigService.getAuthRequest(login.getOpenType());
        AuthCallback callback = AuthCallback.builder().code(login.getCode()).state(login.getState()).build();

        // 根据code，获取用户信息
        AuthResponse<AuthUser> response = authRequest.login(callback);

        // 判断是否成功
        if (!response.ok()) {
            throw new ServerException("第三方登录失败");
        }

        return response.getData().getUuid();
    }
}
