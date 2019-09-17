package com.ncjdjyh.series.micoblog.exception;

/**
 * @Author: ncjdjyh
 * @FirstInitial: 2019/9/17
 * @Description: ~
 */
public class MyException extends RuntimeException {
    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
