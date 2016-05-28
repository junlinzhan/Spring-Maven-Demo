package com.app.mvc.business.service;

import com.app.mvc.acl.convert.BaseConvert;
import com.app.mvc.beans.PageQuery;
import com.app.mvc.beans.PageResult;
import com.app.mvc.business.dao.ProductDao;
import com.app.mvc.business.domain.Product;
import com.app.mvc.business.vo.ProductPara;
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

    public PageResult<Product> getPage(PageQuery page) {
        BaseConvert.checkPara(page);
        int count = productDao.countValid();
        if (count > 0) {
            List<Product> list = productDao.getValidProductList(page);
            return PageResult.<Product>builder().data(list).total(count).build();
        }
        return PageResult.<Product>builder().build();
    }

    public void save(ProductPara para) {
        BaseConvert.checkPara(para);
        Product product = Product.builder().id(para.getId()).title(para.getTitle()).image(para.getImage()).build();
        productDao.save(product);
    }

    public void delete(int id) {
        productDao.invalid(id);
    }
}
