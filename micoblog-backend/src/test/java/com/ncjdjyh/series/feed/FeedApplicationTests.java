package com.ncjdjyh.series.feed;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ncjdjyh.series.feed.entity.Blog;
import com.ncjdjyh.series.feed.mapper.BlogCacheDao;
import com.ncjdjyh.series.feed.mapper.UserTimeLineDao;
import com.ncjdjyh.series.feed.service.IBlogService;
import com.ncjdjyh.series.feed.service.IUserService;
import com.ncjdjyh.series.feed.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IBlogService service;
    @Autowired
    private IUserService userService;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private BlogCacheDao blogCacheDao;
    @Autowired
    private UserTimeLineDao timeLineDao;

    @Test
    public void contextLoads() {
        redisTemplate.opsForValue().set("x", "v");
    }

    @Test
    public void getAllBlogByUserId() {
        service.getAllBlogByUserId(41);
        service.getAllBlogByUserId(42);
        service.getAllBlogByUserId(2);
    }

    @Test
    public void initS0() {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Blog::getUserid, 42);
        List<Blog> list = blogService.list(queryWrapper);
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
        for (Blog blog : list) {
            set.add(new DefaultTypedTuple(blog.getBlogid() + "", DateUtils.parseDoubleDate(blog.getPublishtime())));
        }

        timeLineDao.addList(42, set);
    }

}
