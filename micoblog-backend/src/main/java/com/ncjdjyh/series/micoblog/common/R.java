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
    private int statusCode = 0;
    private Set<String> messages = Collections.emptySet();

    public static R NOT_FIND_RESPONSE = new R().code(5);
    public static R BAD_REQUEST_RESPONSE = new R().code(1);
    public static R UNAUTHORIZED_RESPONSE = new R().code(3);
    public static R FORBIDDEN_RESPONSE = new R().code(2);
    public static R INTERNAL_SERVER_ERROR_RESPONSE = new R().code(4);

    public static R build() {
        return new R();
    }

    public static R build(Object data) {
        return new R().code(0).data(data);
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