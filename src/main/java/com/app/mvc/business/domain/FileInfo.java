package com.app.mvc.business.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jimin on 15/11/29.
 */
@Getter
@Setter
@ToString
public class FileInfo {

    private int id;

    private String originName;

    private String name;

    private String operator;

    private Date operateTime;

    private String md5;

    private long size;

    public FileInfo() {
    }

    public FileInfo(String originName, String name, String operator, String md5, long size) {
        this.originName = originName;
        this.name = name;
        this.operator = operator;
        this.md5 = md5;
        this.size = size;
    }

}
