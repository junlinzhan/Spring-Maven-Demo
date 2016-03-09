package com.app.mvc.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by jimin on 15/11/4.
 */

@ToString
public class PageQuery {

    /**
     * 当前页码
     */
    @Min(value = 1, message = "当前页码不合法")
    @Getter
    @Setter
    private int pageNo = 1;

    /**
     * 每页数目
     */
    @Min(value = 1, message = "每页展示数量不合法")
    @Getter
    @Setter
    private int pageSize = 10;

    /**
     * 偏移量
     */
    @Setter
    private int offset;

    /**
     * 是否使用排序,用于扩展
     * 1:使用,0:不使用
     */
    @Getter
    @Setter
    @Min(0)
    @Max(1)
    private int order = 1;

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }
}
