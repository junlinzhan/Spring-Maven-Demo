package com.app.mvc.util;

import com.google.common.collect.Maps;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;

/**
 * Created by jimin on 15/11/5.
 */
public class BindingResultUtil {

    public static Map<String, Object> getErrors(BindingResult bindingResult) {
        /**
         * 根据bindingResult创建errors信息，最后抽象出来该方法。
         */
        Map<String, Object> errors = Maps.newHashMap();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}
