package com.aowin.model;

/**
 * 盘点库存更新模型
 *
 * @author bingo
 * @date 2020/12/4
 */
public class StockUpdate extends CheckStock {
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
