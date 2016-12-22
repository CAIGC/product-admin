package com.qywenji.product.module.productItemInfo.bean;

import javax.persistence.*;

/**
 * Created by CAI_GC on 2016/12/22.
 */
@Entity
@Table
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    /*商品规格id*/
    private Integer itemId;
    /*商品id*/
    private Integer productId;
    /*规格名称*/
    private String itemName;
    /*规格库存*/
    private Integer quantity;
    /*规格单价*/
    private Float price;
    /*规格类别id*/
    private Integer itemTypeId;
    /*规格类别名，例如：规格，大小，颜色...*/
    private String itemTypeName;
    /*编码*/
    private String productNum;
    /*规格图片*/
    private String itemImageUrl;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }
}
