package com.app.mvc.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * Created by jimin on 15/11/21.
 */
@Getter
@Setter
@ToString
public class Mail {

    private Set<String> receivers; // 收件人的邮箱

    private String subject; // 主题

    private String message; // 信息(支持HTML)

}

