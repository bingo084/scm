package com.aowin.model;

import java.math.BigDecimal;

/**
 * 销售明细单模型
 *
 * @author bingo
 * @date 2020/12/8
 */
public class SoItem {
    private BigDecimal soId;
    private String productCode;
    private Float unitPrice;
    private Integer num;
    private String unitName;
    private Float itemPrice;

    public BigDecimal getSoId() {
        return soId;
    }

    public void setSoId(BigDecimal soId) {
        this.soId = soId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
    }
}
