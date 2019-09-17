package com.ncjdjyh.series.micoblog.common;

/**
 * @Author: ncjdjyh
 * @FirstInitial: 2019/9/17
 * @Description: ~
 */
public enum ResponseEnum {

    SERVICE_SUCCESS(200),
    PARAM_ERROR(4001),
    UNAUTHORIZED(4003),
    NOT_FIND(404),
    SERVICE_ERROR(500);

    ResponseEnum(Integer code) {
        this.code = code;
    }

    private Integer code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
