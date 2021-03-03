package com.aowin.model;

import java.math.BigDecimal;

/**
 * 销售主单模型
 *
 * @author bingo
 * @date 2020/12/7
 */
public class SoMain {
    private BigDecimal soId;
    private String customerCode;
    private String account;
    private String createTime;
    private Float tipFee;
    private Float productTotal;
    private Float poTotal;
    private Integer payType;
    private Float prePayFee;
    private Integer status;
    private String remark;
    private String stockTime;
    private String stockUser;
    private String payTime;
    private String payUser;
    private String prePayTime;
    private String prePayUser;
    private String endTime;
    private String endUser;

    public BigDecimal getSoId() {
        return soId;
    }

    public void setSoId(BigDecimal soId) {
        this.soId = soId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Float getTipFee() {
        return tipFee;
    }

    public void setTipFee(Float tipFee) {
        this.tipFee = tipFee;
    }

    public Float getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(Float productTotal) {
        this.productTotal = productTotal;
    }

    public Float getPoTotal() {
        return poTotal;
    }

    public void setPoTotal(Float poTotal) {
        this.poTotal = poTotal;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Float getPrePayFee() {
        return prePayFee;
    }

    public void setPrePayFee(Float prePayFee) {
        this.prePayFee = prePayFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStockTime() {
        return stockTime;
    }

    public void setStockTime(String stockTime) {
        this.stockTime = stockTime;
    }

    public String getStockUser() {
        return stockUser;
    }

    public void setStockUser(String stockUser) {
        this.stockUser = stockUser;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPrePayTime() {
        return prePayTime;
    }

    public void setPrePayTime(String prePayTime) {
        this.prePayTime = prePayTime;
    }

    public String getPrePayUser() {
        return prePayUser;
    }

    public void setPrePayUser(String prePayUser) {
        this.prePayUser = prePayUser;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndUser() {
        return endUser;
    }

    public void setEndUser(String endUser) {
        this.endUser = endUser;
    }
}
