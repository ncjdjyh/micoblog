package com.ncjdjyh.series.feed.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ncjdjyh.series.feed.entity.Blog;
import com.ncjdjyh.series.feed.entity.BlogDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ncjdjyh
 * @since 2019-08-28
 */
public interface IBlogService extends IService<Blog> {
    List<BlogDetail> getAllBlogOfHome(int userId);

    List<BlogDetail> getAllBlogByUserId(int userId);

    Blog getBlogByBlogId(int id);

    void addBlog(int userId, String content);

    void editBlog(int blogId, String content);

    void deleteBlog(int userId,  int blogId);
}
