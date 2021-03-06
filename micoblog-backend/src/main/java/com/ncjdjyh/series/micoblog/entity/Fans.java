package com.ncjdjyh.series.micoblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ncjdjyh
 * @since 2019-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Fans implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户
     */
    private Integer userId;

    /**
     * 粉丝
     */
    private Integer follower;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
