package net.grape.security.service;

import lombok.AllArgsConstructor;
import net.grape.framework.security.mobile.MobileUserDetailsService;
import net.grape.system.convert.SysUserConvert;
import net.grape.system.dao.SysUserDao;
import net.grape.system.entity.SysUserEntity;
import net.grape.system.service.SysUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 手机验证码登录 MobileUserDetailsService
 *
 * @author 1161095164@qq.com
 *  
 */
@Service
@AllArgsConstructor
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {
    private final SysUserDetailsService sysUserDetailsService;
    private final SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        SysUserEntity userEntity = sysUserDao.getByMobile(mobile);
        if (userEntity == null) {
            throw new UsernameNotFoundException("手机号或验证码错误");
        }

        return sysUserDetailsService.getUserDetails(SysUserConvert.INSTANCE.convertDetail(userEntity));
    }

}
