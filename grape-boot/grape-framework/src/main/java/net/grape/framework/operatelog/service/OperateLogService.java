package net.grape.framework.operatelog.service;

import lombok.AllArgsConstructor;
import net.grape.framework.common.cache.RedisCache;
import net.grape.framework.common.cache.RedisKeys;
import net.grape.framework.operatelog.dto.OperateLogDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 操作日志服务
 *
 * @author 1161095164@qq.com
 *
 */
@Service
@AllArgsConstructor
public class OperateLogService {
    private final RedisCache redisCache;

    @Async
    public void saveLog(OperateLogDTO log) {
        String key = RedisKeys.getLogKey();

        // 保存到Redis队列
        redisCache.leftPush(key, log, RedisCache.NOT_EXPIRE);
    }
}
