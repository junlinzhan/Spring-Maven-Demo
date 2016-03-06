package com.app.mvc.controller;

import com.app.mvc.beans.JsonData;
import com.app.mvc.beans.JsonMapper;
import com.app.mvc.beans.PageQuery;
import com.app.mvc.beans.PageResult;
import com.app.mvc.domain.Product;
import com.app.mvc.exception.ParaException;
import com.app.mvc.service.ProductService;
import com.app.mvc.util.BindingResultUtil;
import com.app.mvc.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Title: ProductController.java
 * Description:
 *
 * @author jimin.zheng
 * @date 15-10-27
 */
@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "page.json", method = RequestMethod.GET)
    public JsonData productQuery(@Valid PageQuery pageQuery, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        if (bindingResult != null && bindingResult.hasErrors()) {
            log.error("参数错误, 错误信息:{}，参数:{}", JsonMapper.obj2String(BindingResultUtil.getErrors(bindingResult)), JsonMapper.obj2String(pageQuery));
            throw new ParaException(JsonMapper.obj2String(BindingResultUtil.getErrors(bindingResult)));
        }
        String ip = IpUtil.getUserIP(request);
        log.info("ip:{}, param:{}", ip, JsonMapper.obj2String(pageQuery));
        List<Product> list = productService.getProductList(pageQuery);
        int total = productService.count();
        PageResult result = PageResult.<Product>builder().total(total).data(list).build();
        log.info("ip:{}, param:{}, result:{}", ip, JsonMapper.obj2String(pageQuery), JsonMapper.obj2Byte(result));
        return JsonData.success(result);
    }
}
