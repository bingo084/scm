package com.aowin.model;

/**
 * 用于在收款登记页面显示销售单信息的model
 *
 * @author bingo
 * @date 2020/12/7
 */
public class SoMainCustomer extends SoMain {
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
