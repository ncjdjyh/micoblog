package com.ncjdjyh.series.micoblog.mapper;

import com.ncjdjyh.series.micoblog.entity.Blog;
import com.ncjdjyh.series.micoblog.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogCacheDao {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private final static String BLOG_NAMESPACE = "blog:";

    public void addBlog(Blog blog) {
        String key = BLOG_NAMESPACE + blog.getBlogid();
        redisTemplate.opsForValue().set(key, SerializeUtil.writeBlogObject(blog));
    }

    public Blog getBlog(int blogId) {
        Blog blog = null;
        String key = BLOG_NAMESPACE + blogId;
        String s = redisTemplate.opsForValue().get(key);

        if (s != null) {
            blog = SerializeUtil.readBlogObject(s);
        }

        return blog;
    }
}
