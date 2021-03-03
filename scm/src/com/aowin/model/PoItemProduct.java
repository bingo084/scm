package com.aowin.model;

/**
 * 销售单明细页面显示模型
 *
 * @author bingo
 * @date 2020/12/8
 */
public class PoItemProduct extends PoItem {
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
