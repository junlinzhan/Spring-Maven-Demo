package com.app.mvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by jimin on 15/11/4.
 */
@ToString
@Getter
@Setter
public class Product {

    private int id;

    private String title;

    private String image;
}
