package com.app.mvc.exception;

/**
 * Created by jimin on 15/11/5.
 */
public class ParaException extends RuntimeException {

    public ParaException() {
        super();
    }

    public ParaException(String message) {
        super(message);
    }

    public ParaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParaException(Throwable cause) {
        super(cause);
    }

    protected ParaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}