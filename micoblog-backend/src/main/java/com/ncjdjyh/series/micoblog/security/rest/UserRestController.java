package com.ncjdjyh.series.micoblog.security.rest;

import com.ncjdjyh.series.micoblog.common.R;
import com.ncjdjyh.series.micoblog.security.SecurityUtils;
import com.ncjdjyh.series.micoblog.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final IUserService userService;

    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public R getActualUser() throws NoSuchFieldException {
        return R.ok(SecurityUtils.getCurrentUsername()
                .map(userService::getUserByNameWithoutPassword)
                .orElseThrow(() -> new NoSuchFieldException()));
    }
}
