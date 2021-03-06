package com.ncjdjyh.series.micoblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ncjdjyh.series.micoblog.entity.Blog;
import com.ncjdjyh.series.micoblog.entity.BlogDetail;
import com.ncjdjyh.series.micoblog.entity.Follow;
import com.ncjdjyh.series.micoblog.entity.User;
import com.ncjdjyh.series.micoblog.mapper.*;
import com.ncjdjyh.series.micoblog.service.IBlogService;
import com.ncjdjyh.series.micoblog.service.IFollowService;
import com.ncjdjyh.series.micoblog.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ncjdjyh
 * @since 2019-08-28
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogCacheDao blogCacheDao;

    @Autowired
    private CacheHitDao cacheHitDao;

    @Autowired
    private UserTimeLineDao userTimeLineDao;

    @Autowired
    private IFollowService followService;

    @Override
    public List<BlogDetail> getAllBlogOfHome(int userId) {
        // 找出当前用户关注的用户
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Follow::getUserId, userId);
        List<Follow> list = followService.list(wrapper);

        Set<Integer> users = list.stream()
                .map(Follow::getFollowedUser)
                .collect(Collectors.toSet());

        users.add(userId);
        // 找出关注用户所发表的微博
        List<Integer> blogs = userTimeLineDao.get(userId, users);
        List<Blog> blogIdList = new ArrayList<>();

        for (int b : blogs) {
            Blog blog = new Blog();
            blog.setBlogid(b);
            blogIdList.add(blog);
        }

        return convert(blogIdList);
    }

    @Override
    public List<BlogDetail> getAllBlogByUserId(int userId) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Blog::getUserid, userId);
        List<BlogDetail> list = convert(blogMapper.selectList(queryWrapper));
        Collections.reverse(list);
        return list;
    }

    private List<BlogDetail> convert(List<Blog> blogIdList) {
        List<BlogDetail> blogDetailList = new ArrayList<>();
        for (Blog blogId : blogIdList) {
            Blog blog = getBlogByBlogId(blogId.getBlogid());
            BlogDetail blogDetail = new BlogDetail();
            blogDetail.setBlogid(blog.getBlogid());
            blogDetail.setContent(blog.getContent());
            blogDetail.setPublishtime(DateUtils.formatDate(blog.getPublishtime()));
            blogDetail.setUserid(blog.getUserid());
            User user = userMapper.selectById(blog.getUserid());
            blogDetail.setUsername(user.getUsername());
            blogDetail.setHeadpic(user.getHeadpic());
            blogDetailList.add(blogDetail);
        }
        return blogDetailList;
    }

    @Override
    public Blog getBlogByBlogId(int blogId) {
        Blog blog = blogCacheDao.getBlog(blogId);
        if (blog != null) {
            cacheHitDao.hit();           /* 缓存命中 */
        } else {
            blog = blogMapper.selectById(blogId);
            cacheHitDao.miss();          /* 缓存未命中 */
            blogCacheDao.addBlog(blog);  /* 放入缓存 */
        }
        return blog;
    }

    @Override
    public void addBlog(int userId, String content) {
        Blog blog = new Blog();
        blog.setUserid(userId);
        blog.setContent(content);
        blog.setPublishtime(new Date());
        blogMapper.insert(blog);
        blogCacheDao.addBlog(blog);
        userTimeLineDao.add(userId, blog.getBlogid());
    }

    @Override
    public void editBlog(int blogId, String content) {
        Blog blog = getBlogByBlogId(blogId);
        blog.setContent(content);
        blogMapper.updateById(blog);
    }

    @Override
    public void deleteBlog(int userId, int blogId) {
        removeById(blogId);
        userTimeLineDao.delete(userId, blogId);
    }
}
