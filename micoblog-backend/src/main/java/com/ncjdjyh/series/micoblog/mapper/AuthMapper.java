package com.ncjdjyh.series.micoblog.mapper;

import com.ncjdjyh.series.micoblog.entity.Auth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ncjdjyh
 * @since 2019-09-17
 */
public interface AuthMapper extends BaseMapper<Auth> {

    List<Auth> listByUserId(@Param("id") int uid);
}
