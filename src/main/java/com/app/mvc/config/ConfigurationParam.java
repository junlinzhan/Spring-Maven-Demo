package com.app.mvc.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by jimin on 15/11/7.
 */
@Getter
@Setter
@ToString
public class ConfigurationParam {

    @NotBlank(message = "key不允许为空")
    private String k;

    private String v;

    private String comment;
}
