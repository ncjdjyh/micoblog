package com.ncjdjyh.series.feed.service.impl;

import com.ncjdjyh.series.feed.entity.User;
import com.ncjdjyh.series.feed.mapper.UserMapper;
import com.ncjdjyh.series.feed.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
