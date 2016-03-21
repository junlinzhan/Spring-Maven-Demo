package com.app.mvc.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 可以动态切换至指定的数据源
 * Created by jimin on 16/03/22.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoutingDataSource {
    DataSources value() default DataSources.MASTER;
}
