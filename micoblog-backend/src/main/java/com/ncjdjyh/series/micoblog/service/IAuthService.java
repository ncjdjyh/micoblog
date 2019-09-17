package com.ncjdjyh.series.micoblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ncjdjyh.series.micoblog.entity.Auth;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ncjdjyh
 * @since 2019-09-17
 */
public interface IAuthService extends IService<Auth> {
    List<Auth> listByUserId(int uid);
}
