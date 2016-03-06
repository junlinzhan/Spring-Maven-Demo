package com.app.mvc.service;

import com.app.mvc.beans.PageQuery;
import com.app.mvc.dao.ProductDao;
import com.app.mvc.domain.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jimin on 15/11/3.
 */
@Service
public class ProductService {

    @Resource
    private ProductDao productDao;

    public List<Product> getProductList(PageQuery pageQuery) {
        return productDao.getProductList(pageQuery);
    }

    public int count() {
        return productDao.count();
    }
}
