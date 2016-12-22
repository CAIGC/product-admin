package com.qywenji.product.module.productInfo.controller;

import com.qywenji.product.commons.controller.BaseController;
import com.qywenji.product.module.productInfo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CAI_GC on 2016/12/21.
 */
@RestController
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    public Object publishProduct(Integer productId){
        productService.getById(productId);
        return super.success();
    }
}
