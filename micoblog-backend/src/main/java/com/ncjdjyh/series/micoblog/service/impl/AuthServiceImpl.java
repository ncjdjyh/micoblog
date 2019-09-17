package com.ncjdjyh.series.micoblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ncjdjyh.series.micoblog.entity.Auth;
import com.ncjdjyh.series.micoblog.mapper.AuthMapper;
import com.ncjdjyh.series.micoblog.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ncjdjyh
 * @since 2019-09-17
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements IAuthService {
    @Autowired
    private AuthMapper mapper;

    @Override
    public List<Auth> listByUserId(int uid) {
        return mapper.listByUserId(uid);
    }
}
