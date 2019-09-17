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
    /**
     * @param userId
     * @description: 查询首页博客
     */
    List<BlogDetail> getAllBlogOfHome(int userId);

    /**
     * @param userId
     * @description: 查询该 userId 的所有博客
     */
    List<BlogDetail> getAllBlogByUserId(int userId);

    /**
     * @param id
     * @description: 通过 id 查询博客
     */
    Blog getBlogByBlogId(int id);

    /**
     * @param userId
     * @param content
     * @description: 添加博客
     */
    void addBlog(int userId, String content);

    /**
     * @param blogId
     * @param content
     * @description: 编辑博客
     */
    void editBlog(int blogId, String content);

    /**
     * @param userId
     * @param blogId
     * @description: 删除博客
     */
    void deleteBlog(int userId,  int blogId);
}
