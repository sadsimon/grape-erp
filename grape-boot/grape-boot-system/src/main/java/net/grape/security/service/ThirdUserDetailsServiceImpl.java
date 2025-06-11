package net.grape.security.service;

import lombok.AllArgsConstructor;
import net.grape.framework.security.third.ThirdUserDetailsService;
import net.grape.system.convert.SysUserConvert;
import net.grape.system.dao.SysUserDao;
import net.grape.system.entity.SysUserEntity;
import net.grape.system.service.SysThirdLoginService;
import net.grape.system.service.SysUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 第三方登录，ThirdUserDetailsService
 *
 * @author 1161095164@qq.com
 *  
 */
@Service
@AllArgsConstructor
public class ThirdUserDetailsServiceImpl implements ThirdUserDetailsService {
    private final SysUserDetailsService sysUserDetailsService;
    private final SysThirdLoginService sysThirdLoginService;
    private final SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByOpenTypeAndOpenId(String openType, String openId) throws UsernameNotFoundException {
        Long userId = sysThirdLoginService.getUserIdByOpenTypeAndOpenId(openType, openId);
        SysUserEntity userEntity = sysUserDao.getById(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException("绑定的系统用户，不存在");
        }

        return sysUserDetailsService.getUserDetails(SysUserConvert.INSTANCE.convertDetail(userEntity));
    }
}
