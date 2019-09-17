package com.ncjdjyh.series.micoblog.exception;

/**
 * @Author: ncjdjyh
 * @FirstInitial: 2019/9/17
 * @Description: ~
 */
public class DatabaseDataConflictException extends RuntimeException {
    public DatabaseDataConflictException(String reason) {
        super(reason);
    }

    public DatabaseDataConflictException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
