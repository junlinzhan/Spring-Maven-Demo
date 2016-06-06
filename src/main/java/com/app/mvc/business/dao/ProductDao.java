package com.app.mvc.business.dao;

import com.app.mvc.beans.PageQuery;
import com.app.mvc.business.domain.Product;
import com.app.mvc.common.DBRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jimin on 15/11/4.
 */
@DBRepository
public interface ProductDao {

    List<Product> getValidProductList(PageQuery pageQuery);

    int countValid();

    void save(Product product);

    void invalid(@Param("id") int id);
}
