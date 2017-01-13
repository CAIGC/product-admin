package com.qywenji.product.module.productInfo.controller;

import com.qywenji.product.commons.controller.BaseController;
import com.qywenji.product.module.productInfo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CAI_GC on 2016/12/21.
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    public Object publishProduct(Integer productId){
        productService.getById(productId);
        return super.success();
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object page(HttpServletRequest request, @RequestParam(defaultValue = "1", required = false) int pageNo, @RequestParam(defaultValue = "100", required = false) int pageSize) {
        Integer start = (pageNo - 1) * pageSize;
        return super.success(productService.getPage(super.createListAllProperties(request), start, pageSize));
    }

}
