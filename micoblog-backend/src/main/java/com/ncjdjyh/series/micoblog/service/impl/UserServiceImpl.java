package com.ncjdjyh.series.micoblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ncjdjyh.series.micoblog.entity.User;
import com.ncjdjyh.series.micoblog.mapper.UserMapper;
import com.ncjdjyh.series.micoblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ncjdjyh
 * @since 2019-08-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public User getUserByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, name);
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public User getUserByNameWithoutPassword(String name) {
        User user = getUserByName(name);
        user.setMd5password("");
        return user;
    }
}
