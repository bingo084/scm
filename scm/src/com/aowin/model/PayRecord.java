package com.aowin.model;

import java.math.BigDecimal;

/**
 * 收付款登记表模型
 *
 * @author bingo
 * @date 2020/12/8
 */
public class PayRecord {
    private Integer recordId;
    private String payTime;
    private BigDecimal payPrice;
    private String account;
    private String orderCode;
    private Integer payType;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer mainPayType) {
        this.payType = mainPayType;
    }
}
