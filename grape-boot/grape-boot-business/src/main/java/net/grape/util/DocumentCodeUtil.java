package net.grape.util;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import net.grape.enums.DocumentEnum;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
public class DocumentCodeUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public String generateOrderNumber(String type) {
        // 1. 获取日期部分
        String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 2. 构造Redis键
        String redisKey = "order:" + datePart;

        // 3. Redis原子递增操作
        Long sequence = redisTemplate.opsForValue().increment(redisKey);

        // 4. 设置键的过期时间
        if(sequence == 1) {
            redisTemplate.expire(redisKey, 24, TimeUnit.HOURS);
        }

        // 5. 组合最终单据编号
        return DocumentEnum.getprefixByValue(type) + datePart + String.format("%05d", sequence % 100000);
    }

}
