package com.ncjdjyh.series.micoblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ncjdjyh.series.micoblog.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ncjdjyh
 * @since 2019-08-28
 */
public interface IUserService extends IService<User> {
    User getUserByName(String name);

    User getUserByNameWithoutPassword(String name);
}
