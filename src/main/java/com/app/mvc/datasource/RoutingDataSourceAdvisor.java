package com.app.mvc.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * 动态数据源Advisor
 * Created by jimin on 16/03/22.
 */
@Slf4j
@Component
@Aspect
@Order(AspectOrder.ROUTING_DATA_SOURCE)
public class RoutingDataSourceAdvisor {

    @Pointcut("execution(@com.app.mvc.datasource.RoutingDataSource * com.app.mvc..*Service+.*(..))")
    private void routingDataSource() {
    }

    @Around("routingDataSource()")
    public Object routing(ProceedingJoinPoint joinPoint) throws Exception {

        Class<?> clazz = joinPoint.getTarget().getClass();
        String className = clazz.getName();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getName();
        Object[] arguments = joinPoint.getArgs();

        String key;
        RoutingDataSource routingDataSource = method.getAnnotation(RoutingDataSource.class);
        key = routingDataSource.value().getKey();

        log.info("Routing to datasource({}) in {}.{}, args={}", key, className, methodName, arguments);

        Object result = null;
        DataSourceKeyHolder.set(key);

        try {
            checkIfCompatible(clazz, method);
            result = joinPoint.proceed(arguments);
        } catch (Throwable e) {
            log.error("Error occurred during datasource(key=" + key + ") routing, ", e);
        } finally {
            DataSourceKeyHolder.clear();
        }
        return result;
    }

    private void checkIfCompatible(Class<?> clazz, Method method) {
        if (DataSourceKeyHolder.isNestedCall()) {
            Transactional transactional = method.getAnnotation(Transactional.class);
            if (transactional == null) {
                transactional = clazz.getAnnotation(Transactional.class);
            }
            if (transactional != null) {
                if (transactional.propagation() != Propagation.REQUIRES_NEW) {
                    throw new RuntimeException("@RoutingDataSource方法嵌套调用时, 如果内部方法具有@Transactional注解, 必须定义为propagation = REQUIRES_NEW");
                }
            }
        }
    }

}
