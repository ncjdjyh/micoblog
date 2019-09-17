package com.ncjdjyh.series.micoblog.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 读写缓存命中率数据
 **/
@Repository
public class CacheHitDao {
    @Autowired
    RedisTemplate<String, Integer> redisTemplate;

    private static final String CACHE_HIT_KEY = "cache-hit";
    private static final String CACHE_MISS_KEY = "cache-miss";

    public void hit() {
        redisTemplate.opsForValue().increment(CACHE_HIT_KEY);
    }

    public void miss() {
        redisTemplate.opsForValue().increment(CACHE_MISS_KEY);
    }
}
