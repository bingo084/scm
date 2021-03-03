package com.aowin.model;

import java.math.BigDecimal;

/**
 * 月度收支报表主信息模型
 *
 * @author bingo
 * @date 2020/12/10
 */
public class IncomeMain {
    private BigDecimal paySum;
    private BigDecimal receiveSum;
    private Integer payCount;
    private Integer receiveCount;

    public BigDecimal getPaySum() {
        return paySum;
    }

    public void setPaySum(BigDecimal paySum) {
        this.paySum = paySum;
    }

    public BigDecimal getReceiveSum() {
        return receiveSum;
    }

    public void setReceiveSum(BigDecimal receiveSum) {
        this.receiveSum = receiveSum;
    }

    public Integer getPayCount() {
        return payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    public Integer getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(Integer receiveCount) {
        this.receiveCount = receiveCount;
    }
}
