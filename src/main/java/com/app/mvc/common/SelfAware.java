package com.app.mvc.common;

/**
 * Created by jimin on 16/03/22.
 */
public interface SelfAware<T> {

    /**
     * spring上下文bean自感接口
     */
    T self();
}
