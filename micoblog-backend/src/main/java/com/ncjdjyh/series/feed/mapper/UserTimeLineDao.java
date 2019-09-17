package com.ncjdjyh.series.feed.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserTimeLineDao {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    // 自己发布文章的 s0
    private final static String TIME_LINE_NAMESPACE = "time_line0:";
    // 关注人发布文章的 s1
    private final static String TIME_LINE_0_NAMESPACE = "time_line1";

    /**
     * @param userId
     * @param blogId
     * @description: 添加文章到 s0 中
     */
    public void add(Integer userId, Integer blogId) {
        String key = buildKey(userId);
        BoundZSetOperations<String, String> ops = redisTemplate.boundZSetOps(key);
        ops.add(blogId + "", (double) System.currentTimeMillis());
        long size = ops.size();
        final long MAX_NUM = 100;
        if (size > MAX_NUM) {
            // 大小限制为 100 超过限制删除最旧的文章
            ops.removeRange(0, 0);
        }
    }

    /**
     * @param userId
     * @description: 添加文章到 s0 中
     */
    public void addList(Integer userId, Set<ZSetOperations.TypedTuple<String>> set) {
        String key = buildKey(userId);
        BoundZSetOperations<String, String> ops = redisTemplate.boundZSetOps(key);
        ops.add(set);
        long size = ops.size();
        final long MAX_NUM = 100;
        if (size > MAX_NUM) {
            // 大小限制为 100 超过限制删除最旧的文章
            ops.removeRange(0, 0);
        }
    }

    /**
     * @param userId
     * @param focusUserIds
     * @description: 获取 feed 应该显示的文章
     */
    public List<Integer> get(Integer userId, Set<Integer> focusUserIds) {
        BoundZSetOperations<String, String> tops0 = redisTemplate.boundZSetOps(buildKey1(userId));
        double nts = 0;
        // 找出关注者中最新的时间戳 nts
        Set<ZSetOperations.TypedTuple<String>> set = tops0.rangeWithScores(-1, -1);

        for (ZSetOperations.TypedTuple<String> t : set) {
            nts = t.getScore();
        }

        // 从关注者的 s0 中找出晚于 nts 的文章加入该用户的 s1 中
        for (Integer id : focusUserIds) {
            String key = buildKey(id);
            Set<ZSetOperations.TypedTuple<String>> set1 = redisTemplate.opsForZSet().rangeByScoreWithScores(key, nts, Double.MAX_VALUE);
            for (ZSetOperations.TypedTuple<String> s : set1) {
                tops0.add(s.getValue(), s.getScore());
            }
        }

        // 限制获取的最大文章数量为 50 条
        int MAX_NUM = 50;
        long size = tops0.size();
        tops0.removeRange(0, size - MAX_NUM);
        List<ZSetOperations.TypedTuple<String>> list = new ArrayList<>(tops0.rangeWithScores(0, -1));

        list.sort((o1, o2) -> {
            if (o1.getScore() > o2.getScore()) {
                return -1;
            }
            if (o1.getScore() < o2.getScore()) {
                return 1;
            }
            return 0;
        });

        List<Integer> collect = list.stream()
                .map(ZSetOperations.TypedTuple::getValue)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        return collect;
    }

    public void delete(Integer userId, Integer blogId) {
        String key = buildKey(userId);
        redisTemplate.opsForZSet().remove(key, blogId + "");
    }

    private String buildKey(Integer userId) {
        return TIME_LINE_NAMESPACE + userId;
    }

    private String buildKey1(Integer userId) {
        return TIME_LINE_0_NAMESPACE + userId;
    }
}
