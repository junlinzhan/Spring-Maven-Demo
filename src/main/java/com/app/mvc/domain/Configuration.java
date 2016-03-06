package com.app.mvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by jimin on 15/11/7.
 */
@Getter
@Setter
@ToString
public class Configuration {

    private String k;

    private String v;

    private String operator;

    private String comment;

    public Configuration() {
    }

    public Configuration(String k, String v, String operator, String comment) {
        this.k = k;
        this.v = v;
        this.operator = operator;
        this.comment = comment;
    }
}
