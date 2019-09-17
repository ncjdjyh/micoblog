package com.ncjdjyh.series.micoblog.controller;

import com.ncjdjyh.series.micoblog.entity.BlogDetail;
import com.ncjdjyh.series.micoblog.entity.User;
import com.ncjdjyh.series.micoblog.service.IBlogService;
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
