package com.app.mvc.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;

/**
 * Created by jimin on 15/11/21.
 */
@Slf4j
public class SpringHelper {

    private static ApplicationContext applicationContext;

    /**
     * @param applicationContext
     * @see ApplicationContextHelper
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringHelper.applicationContext = applicationContext;
    }

    public static <T> T popBean(Class<T> clazz) {
        if (applicationContext == null)
            return null;
        return applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String name, Class<T> clazz) {
        if (applicationContext == null)
            return null;
        return applicationContext.getBean(name, clazz);
    }

    public static RabbitTemplate popRabbitTemplate(String provider) {
        if(applicationContext == null) return null;
        Object bean = applicationContext.getBean(provider);
        if (bean instanceof RabbitTemplate) {
            return (RabbitTemplate)bean;
        } else {
            throw new RuntimeException("无法找到rabbitTemplate，但是找到了对应的其他类型bean:"
                    + bean.toString());
        }
    }
}
