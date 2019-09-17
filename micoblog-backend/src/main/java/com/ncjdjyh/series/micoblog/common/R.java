package com.ncjdjyh.series.micoblog.common;

/**
 * @Author: ncjdjyh
 * @FirstInitial: 2019/9/17
 * @Description: ~
 */
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Set;

@Data
public class R {
    private Object data;
    private int statusCode = ResponseEnum.SERVICE_SUCCESS.getCode();
    private Set<String> messages = Collections.emptySet();

    public static R NOT_FIND_RESPONSE = new R().code(ResponseEnum.NOT_FIND.getCode());
    public static R BAD_REQUEST_RESPONSE = new R().code(ResponseEnum.PARAM_ERROR.getCode());
    public static R UNAUTHORIZED_RESPONSE = new R().code(ResponseEnum.UNAUTHORIZED.getCode());
    public static R INTERNAL_SERVER_ERROR_RESPONSE = new R().code(ResponseEnum.SERVICE_ERROR.getCode());
    public static R OK_RESPONSE = new R().code(ResponseEnum.SERVICE_SUCCESS.getCode());

    public static R build() {
        return new R();
    }

    public static R build(Object data) {
        return new R().data(data);
    }

    public static R ok(Object data) {
        return build(data);
    }

    public static R done() {
        return build().msg(null).data(new JSONObject());
    }

    public R code(int code) {
        this.statusCode = code;
        return this;
    }

    public R data(Object data) {
        this.data = data;
        return this;
    }

    public R msg(String msg) {
        boolean emptyMsg = StringUtils.isEmpty(msg);
        this.messages = emptyMsg
                ? Collections.emptySet()
                : Collections.singleton(msg);
        return this;
    }
}