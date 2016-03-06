package com.app.mvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by jimin on 15/11/2.
 */
@Getter
@Setter
@ToString
public class Admin {

    /**
     * 有效状态标识
     */
    public final static int STATUS_VALID = 1;

    private int id;

    private String username;

    private String password;

    private int status;
}
