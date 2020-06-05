package top.wayneshen.slk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 获取redis key
 *
 * @author wayne
 * @version 1.0
 * @date 2020年05月22日
 */
@Component
public class RedisKeyUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.application.name}")
    private String serverName;

    public String getShortLinkKey(String code) {
        return serverName + "-shortLink-" + code;
    }

    /**
     * Atomically increments by one the current value.
     *
     * @return
     * @Description:
     */
    public long generateIncrementId() {
        String key = serverName + "-shortLink-auto-increment";
        RedisAtomicLong counter = new RedisAtomicLong(key, stringRedisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }


}
