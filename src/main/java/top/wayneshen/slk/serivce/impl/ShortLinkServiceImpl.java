package top.wayneshen.slk.serivce.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.wayneshen.slk.config.RedisKeyUtil;
import top.wayneshen.slk.serivce.ShortLinkService;
import top.wayneshen.slk.utils.ConversionUtil;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * 短链接service 实现类
 *
 * @Author Shenwh
 * @Date 2020/6/2 11:03
 */
@Service
@Slf4j
public class ShortLinkServiceImpl implements ShortLinkService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisKeyUtil redisKeyUtil;

    @Override
    public String generateShortLink(String originLink, String validTime) {
        // 从redis中拿到  incrementId
        long id = redisKeyUtil.generateIncrementId();
        String code = ConversionUtil.encode(id);
        String key = redisKeyUtil.getShortLinkKey(code);
        if (StringUtils.isNotBlank(validTime)) {
            stringRedisTemplate.opsForValue().set(key,originLink,Long.parseLong(validTime), TimeUnit.MINUTES);
        } else {
            stringRedisTemplate.opsForValue().set(key, originLink);
        }
        return code;
    }

    @Override
    public String findShortLink(String code) {
        String key = redisKeyUtil.getShortLinkKey(code);
        return stringRedisTemplate.opsForValue().get(key);
    }

}
