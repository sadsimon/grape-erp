package net.grape.sms.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.exception.ServerException;
import net.grape.framework.common.utils.ExceptionUtils;
import net.grape.framework.common.utils.JsonUtils;
import net.grape.sms.SmsContext;
import net.grape.sms.config.SmsConfig;
import net.grape.system.cache.SmsConfigCache;
import net.grape.system.entity.SysSmsLogEntity;
import net.grape.system.service.SysSmsConfigService;
import net.grape.system.service.SysSmsLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 短信服务
 *
 * @author 1161095164@qq.com
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class SmsService {
    private final SysSmsConfigService sysSmsConfigService;
    private final SysSmsLogService sysSmsLogService;
    private final SmsConfigCache smsConfigCache;

    /**
     * 发送短信
     *
     * @param mobile 手机号
     * @return 是否发送成功
     */
    public boolean send(String mobile) {
        return this.send(mobile, MapUtil.newHashMap());
    }

    /**
     * 发送短信
     *
     * @param groupName 分组名称
     * @param mobile    手机号
     * @return 是否发送成功
     */
    public boolean send(String groupName, String mobile) {
        return this.send(groupName, mobile, MapUtil.newHashMap());
    }

    /**
     * 发送短信
     *
     * @param mobile 手机号
     * @param params 参数
     * @return 是否发送成功
     */
    public boolean send(String mobile, Map<String, String> params) {
        return this.send(null, mobile, params);
    }

    /**
     * 发送短信
     *
     * @param groupName 分组名称
     * @param mobile    手机号
     * @param params    参数
     * @return 是否发送成功
     */
    public boolean send(String groupName, String mobile, Map<String, String> params) {
        SmsConfig config = roundSmsConfig(groupName);

        try {
            // 发送短信
            new SmsContext(config).send(mobile, params);

            saveLog(config, mobile, params, null);
            return true;
        } catch (Exception e) {
            log.error("短信发送失败，手机号：{}", mobile, e);

            saveLog(config, mobile, params, e);

            return false;
        }
    }

    /**
     * 保存短信日志
     */
    public void saveLog(SmsConfig config, String mobile, Map<String, String> params, Exception e) {
        SysSmsLogEntity logEntity = new SysSmsLogEntity();
        logEntity.setPlatform(config.getPlatform());
        logEntity.setPlatformId(config.getId());
        logEntity.setMobile(mobile);
        logEntity.setParams(JsonUtils.toJsonString(params));

        if (e != null) {
            String error = StringUtils.substring(ExceptionUtils.getExceptionMessage(e), 0, 2000);
            logEntity.setStatus(Constant.FAIL);
            logEntity.setError(error);
        } else {
            logEntity.setStatus(Constant.SUCCESS);
        }

        sysSmsLogService.save(logEntity);
    }

    /**
     * 通过轮询算法，获取短信平台的配置
     *
     * @param groupName 分组名称
     * @return 短信平台配置
     */
    private SmsConfig roundSmsConfig(String groupName) {
        List<SmsConfig> platformList = sysSmsConfigService.listByEnable();

        // 是否有可用的短信平台
        if (platformList.isEmpty()) {
            throw new ServerException("没有可用的短信平台，请先添加");
        }

        // 没有短信编码的情况
        if (StrUtil.isBlank(groupName)) {
            // 采用轮询算法，发送短信
            long round = smsConfigCache.getRoundValue();

            return platformList.get((int) round % platformList.size());
        }


        // 有短信编码的情况
        List<SmsConfig> newList = platformList.stream().filter(platform -> StrUtil.equals(platform.getGroupName(), groupName)).toList();
        if (newList.isEmpty()) {
            throw new ServerException("短信分组不存在");
        }

        long round = smsConfigCache.getRoundCodeValue();

        // 指定短信平台
        return newList.get((int) round % newList.size());
    }

}
