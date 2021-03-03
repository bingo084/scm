package com.aowin.model;

/**
 * 用于在付款登记页面显示销售单信息的model
 *
 * @author bingo
 * @date 2020/12/8
 */
public class PoMainVendor extends PoMain {
    private String vendorName;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
