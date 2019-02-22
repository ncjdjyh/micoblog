package com.cyc.common;

import com.cyc.util.PropertiesUtil;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.util.Set;

@Component
public class RedisPool {
    private static JedisPool pool;

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(PropertiesUtil.getIntegerProperty("redis.maxTotal"));
        config.setMaxIdle(PropertiesUtil.getIntegerProperty("redis.maxIdle"));
        config.setMinIdle(PropertiesUtil.getIntegerProperty("redis.minIdle"));
        pool = new JedisPool(config,
                PropertiesUtil.getProperty("redis.ip", "localhost"),
                PropertiesUtil.getIntegerProperty("redis.port", "6379"),
                PropertiesUtil.getIntegerProperty("redis.timeout", "2000"));
    }


    static {
        initPool();
    }


    public static String set(String key, String value) {
        String result = null;
        try (Jedis jedis = RedisPool.getResource()) {
            result = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String get(String key) {

        String result = null;
        try (Jedis jedis = RedisPool.getResource()) {
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String setEx(String key, String value, int exTime) {

        String result = null;

        try (Jedis jedis = RedisPool.getResource()) {
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Long setEx(String key, int exTime) {

        Long result = null;

        try (Jedis jedis = RedisPool.getResource()) {
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Long del(String key) {

        Long result = null;

        try (Jedis jedis = RedisPool.getResource()) {
            result = jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Long zadd(String key, Double score, String member) {

        Long result = null;

        try (Jedis jedis = RedisPool.getResource()) {
            result = jedis.zadd(key, score, member);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Set<String> zrange(String key, long start, long stop) {

        Set<String> result = null;

        try (Jedis jedis = RedisPool.getResource()) {
            result = jedis.zrange(key, start, stop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Set<Tuple> zrangeWithScores(String key, long start, long stop) {

        Set<Tuple> result = null;

        try (Jedis jedis = RedisPool.getResource()) {
            result = jedis.zrangeWithScores(key, start, stop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static long zcard(String key) {

        Long result = null;

        try (Jedis jedis = RedisPool.getResource()) {
            result = jedis.zcard(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Jedis getResource() {
        return pool.getResource();
    }
}
