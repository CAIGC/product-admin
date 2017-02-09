package com.qywenji.product.module.productInfo.controller;

import com.qywenji.product.commons.controller.BaseController;
import com.qywenji.product.module.productInfo.bean.Product;
import com.qywenji.product.module.productInfo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    /**
     * 发布商品
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/publish")
    public Object publishProduct(String ids) {
        String[] productIds = ids.split(",");
        for (String id : productIds) {
            Boolean flag = productService.publish(Integer.parseInt(id));
            if (!flag) {
                logger.info("******产品:{},发布失败", id);
            }
        }
        return super.success();
    }

    /**
     * 下架商品
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/publishCancel")
    public Object publishCancel(String ids) {
        String[] productIds = ids.split(",");
        for (String id : productIds) {
            Boolean flag = productService.publishCancel(Integer.parseInt(id));
            if (!flag) {
                logger.info("******产品:{},下架失败", id);
            }
        }
        return super.success();
    }

    /**
     * 下架商品
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    public Object publishDelete(String ids) {
        String[] productIds = ids.split(",");
        for (String id : productIds) {
            Boolean flag = productService.publishDelete(Integer.parseInt(id));
            if (!flag) {
                logger.info("******产品:{},删除失败", id);
            }
        }
        return super.success();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object page(HttpServletRequest request, @RequestParam(defaultValue = "1", required = false) int pageNo, @RequestParam(defaultValue = "100", required = false) int pageSize) {
        Integer start = (pageNo - 1) * pageSize;
        return super.success(productService.getPage(super.createListAllProperties(request), start, pageSize));
    }

}
