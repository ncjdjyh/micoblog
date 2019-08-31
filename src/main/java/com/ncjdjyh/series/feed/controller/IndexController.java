package com.ncjdjyh.series.feed.controller;

import com.ncjdjyh.series.feed.entity.BlogDetail;
import com.ncjdjyh.series.feed.entity.User;
import com.ncjdjyh.series.feed.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private IBlogService blogServiceImp;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/feed/home")
    public List<BlogDetail> showIndexPage(User user) {
        List<BlogDetail> allBlog = blogServiceImp.getAllBlogOfHome(user.getUserid());
        return allBlog;
    }
}
