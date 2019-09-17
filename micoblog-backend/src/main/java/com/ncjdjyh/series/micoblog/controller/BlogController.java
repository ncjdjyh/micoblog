package com.ncjdjyh.series.micoblog.controller;


import com.ncjdjyh.series.micoblog.entity.Blog;
import com.ncjdjyh.series.micoblog.entity.User;
import com.ncjdjyh.series.micoblog.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ncjdjyh
 * @since 2019-08-28
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    BlogServiceImpl service;

    @PostMapping
    public void publishBlog(Blog blog, User user) {
        int len = blog.getContent().length();
        final int MAX_LEN = 1000;
        if (len > 0 && len < MAX_LEN && user != null) {
            service.addBlog(user.getUserid(), blog.getContent());
        }
    }
}

