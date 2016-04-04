package com.app.mvc.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * Created by jimin on 15/11/21.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mail {

    private Set<String> receivers; // 收件人的邮箱

    private String subject; // 主题

    private String message; // 信息(支持HTML)

}

