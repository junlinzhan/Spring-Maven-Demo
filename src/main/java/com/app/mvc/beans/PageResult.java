package com.app.mvc.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by jimin on 15/11/4.
 */
@Getter
@Setter
@ToString
@Builder
public class PageResult<T> {

    private List<T> data;

    private int total;
}
