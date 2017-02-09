package com.qywenji.product.module.productInfo.service;

import com.alibaba.fastjson.JSONObject;
import com.qywenji.product.commons.service.BaseService;
import com.qywenji.product.module.mq.ProductQueueMessageProducer;
import com.qywenji.product.module.productInfo.bean.Product;
import com.qywenji.product.module.productInfo.constant.ProductConstant;
import com.qywenji.product.module.productItemInfo.bean.ProductItem;
import com.qywenji.product.module.productItemInfo.service.ProductItemService;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CAI_GC on 2016/12/21.
 */
@Service
public class ProductService extends BaseService<Product> {

    @Autowired
    private ProductItemService productItemService;

    public Boolean publish(Integer productId) {
        Product product = this.getById(productId);
        List<ProductItem> productItemList = productItemService.getByProductId(productId);
        if (product == null || productItemList.isEmpty()) {
            return false;
        }
        product.setStatus(ProductConstant.ON_SHELF);
        this.save(product);
        /**发送mq**/
        Map<String, Object> sendMap = new HashMap<>();
        sendMap.put("type", "publish");
        JSONObject data = new JSONObject();
        data.put("productJson", JSONObject.toJSONString(product));
        data.put("productItemListJson", JSONObject.toJSONString(productItemList));
        sendMap.put("data", data);
        ProductQueueMessageProducer.send(sendMap);
        /**发送mq**/
        return true;
    }

    public Boolean publishCancel(Integer productId) {

        Product product = this.getById(productId);
        List<ProductItem> productItemList = productItemService.getByProductId(productId);
        if (product == null || productItemList.isEmpty()) {
            return false;
        }
        product.setStatus(ProductConstant.OFF_SHELF);
        this.save(product);
        /**发送mq**/
        Map<String, Object> sendMap = new HashMap<>();
        sendMap.put("type", "cancel");
        JSONObject data = new JSONObject();
        data.put("productJson", JSONObject.toJSONString(product));
        data.put("productItemListJson", JSONObject.toJSONString(productItemList));
        sendMap.put("data", data);
        ProductQueueMessageProducer.send(sendMap);
        /**发送mq**/
        return true;
    }

    public Boolean publishDelete(Integer productId) {
        Product product = this.getById(productId);
        product.setStatus(ProductConstant.DELETE);
        this.del(product);
        productItemService.delByProductId(productId);
        /**发送mq**/
        Map<String, Object> sendMap = new HashMap<>();
        sendMap.put("type", "delete");
        JSONObject data = new JSONObject();
        data.put("productId", productId);
        sendMap.put("data", data);
        ProductQueueMessageProducer.send(sendMap);
        /**发送mq**/
        return true;
    }
}
