package com.ncjdjyh.series.micoblog.util;

import com.ncjdjyh.series.micoblog.entity.Blog;

/**
 * @Author: ncjdjyh
 * @FirstInitial: 2019/8/29
 * @Description: ~
 */
public class SerializeUtil {

    private static final String separator = "/////";

    /**
     * @param blog
     * @description: 将博客序列化为字符串, 主要用于写入 redis
     */
    public static String writeBlogObject(Blog blog) {
        StringBuilder s = new StringBuilder();
        s.append(blog.getUserid()).append(separator);
        s.append(blog.getBlogid()).append(separator);
        s.append(DateUtils.formatDate(blog.getPublishtime())).append(separator);
        s.append(blog.getContent());
        return s.toString();
    }

    /**
     * @param s
     * @description: 按照写入时的规则从 redis 中读取博客
     */
    public static Blog readBlogObject(String s) {
        Blog blog = new Blog();
        String[] token = s.split(separator);
        blog.setUserid(Integer.valueOf(token[0]));
        blog.setBlogid(Integer.valueOf(token[1]));
        blog.setPublishtime(DateUtils.parseDate(token[2]));
        if (token.length > 3) {
            blog.setContent(token[3]);
        }
        return blog;
    }
}

