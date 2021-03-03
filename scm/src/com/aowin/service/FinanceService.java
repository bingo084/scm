package com.aowin.service;

import com.aowin.dao.*;
import com.aowin.model.*;
import com.aowin.util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 收支管理service
 *
 * @author bingo
 * @date 2020/12/7
 */
public class FinanceService {
    /**
     * 通过销售单付款方式和处理状态获取销售单页面对象
     *
     * @param soMainPage 销售单分页对象
     * @param payType    销售单付款方式
     * @param statuses   销售单处理状态数组
     * @return 销售单页面对象list
     */
    public Page<SoMainCustomer> getSoMainCustomers(Page<SoMain> soMainPage, String payType, String[] statuses) {
        // 根据销售单付款方式、处理状态查询销售单
        soMainPage = new SoMainDao().getSoMains(soMainPage, payType, statuses);
        // 创建页面显示分页对象
        Page<SoMainCustomer> soMainCustomerPage = new Page<>();
        List<SoMainCustomer> soMainCustomers = new ArrayList<>();
        if (soMainPage.getDataList() != null) {
            for (SoMain soMain : soMainPage.getDataList()) {
                SoMainCustomer soMainCustomer = new SoMainCustomer();
                soMainCustomer.setSoId(soMain.getSoId());
                soMainCustomer.setCustomerName(new CustomerDao().get(soMain.getCustomerCode()).getName());
                soMainCustomer.setAccount(soMain.getAccount());
                soMainCustomer.setCreateTime(soMain.getCreateTime());
                soMainCustomer.setTipFee(soMain.getTipFee());
                soMainCustomer.setProductTotal(soMain.getProductTotal());
                soMainCustomer.setPoTotal(soMain.getPoTotal());
                soMainCustomer.setPayType(soMain.getPayType());
                soMainCustomer.setPrePayFee(soMain.getPrePayFee());
                soMainCustomer.setStatus(soMain.getStatus());
                soMainCustomer.setRemark(soMain.getRemark());
                soMainCustomer.setStockTime(soMain.getStockTime());
                soMainCustomer.setStockUser(soMain.getStockUser());
                soMainCustomer.setPayTime(soMain.getPayTime());
                soMainCustomer.setPayUser(soMain.getPayUser());
                soMainCustomer.setPrePayTime(soMain.getPrePayTime());
                soMainCustomer.setPrePayUser(soMain.getPrePayUser());
                soMainCustomer.setEndTime(soMain.getEndTime());
                soMainCustomer.setEndUser(soMain.getEndUser());
                soMainCustomers.add(soMainCustomer);
            }
            soMainCustomerPage.setDataList(soMainCustomers);
        }
        soMainCustomerPage.setCurrentPage(soMainPage.getCurrentPage());
        soMainCustomerPage.setPageSize(soMainPage.getPageSize());
        soMainCustomerPage.setTotalPage(soMainPage.getTotalPage());
        soMainCustomerPage.setTotalRecord(soMainPage.getTotalRecord());
        return soMainCustomerPage;
    }

    public List<SoItemProduct> getSoItem(String soId) {
        List<SoItem> soItems = new SoItemDao().get(soId);
        List<SoItemProduct> soItemProducts = new ArrayList<>();
        for (SoItem soItem : soItems) {
            SoItemProduct soItemProduct = new SoItemProduct();
            soItemProduct.setSoId(soItem.getSoId());
            soItemProduct.setProductCode(soItem.getProductCode());
            soItemProduct.setProductName(new ProductDao().get(soItem.getProductCode()).getName());
            soItemProduct.setUnitPrice(soItem.getUnitPrice());
            soItemProduct.setNum(soItem.getNum());
            soItemProduct.setUnitName(soItem.getUnitName());
            soItemProduct.setItemPrice(soItem.getItemPrice());
            soItemProducts.add(soItemProduct);
        }
        return soItemProducts;
    }

    public SoMainCustomer getSoMainCustomer(String soId) {
        SoMain soMain = new SoMainDao().getSoMain(soId);
        SoMainCustomer soMainCustomer = new SoMainCustomer();
        soMainCustomer.setSoId(soMain.getSoId());
        soMainCustomer.setCustomerName(new CustomerDao().get(soMain.getCustomerCode()).getName());
        soMainCustomer.setAccount(soMain.getAccount());
        soMainCustomer.setCreateTime(soMain.getCreateTime());
        soMainCustomer.setTipFee(soMain.getTipFee());
        soMainCustomer.setProductTotal(soMain.getProductTotal());
        soMainCustomer.setPoTotal(soMain.getPoTotal());
        soMainCustomer.setPayType(soMain.getPayType());
        soMainCustomer.setPrePayFee(soMain.getPrePayFee());
        soMainCustomer.setStatus(soMain.getStatus());
        soMainCustomer.setRemark(soMain.getRemark());
        soMainCustomer.setStockTime(soMain.getStockTime());
        soMainCustomer.setStockUser(soMain.getStockUser());
        soMainCustomer.setPayTime(soMain.getPayTime());
        soMainCustomer.setPayUser(soMain.getPayUser());
        soMainCustomer.setPrePayTime(soMain.getPrePayTime());
        soMainCustomer.setPrePayUser(soMain.getPrePayUser());
        soMainCustomer.setEndTime(soMain.getEndTime());
        soMainCustomer.setEndUser(soMain.getEndUser());
        return soMainCustomer;
    }

    public void addPayRecord(PayRecord payRecord, HttpServletRequest req) {
        Connection conn = DBUtil.getConn();
        try {
            conn.setAutoCommit(false);
            new PayRecordDao().add(conn, payRecord);
            if (payRecord.getPayType() == 1 || payRecord.getPayType() == 3) {
                new SoMainDao().edit(conn, payRecord);
                conn.commit();
                req.setAttribute("msg", "收款登记成功！");
            } else if (payRecord.getPayType() == 2 || payRecord.getPayType() == 4) {
                new PoMainDao().edit(conn, payRecord);
                conn.commit();
                req.setAttribute("msg", "付款登记成功！");
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if (payRecord.getPayType() == 1 || payRecord.getPayType() == 3) {
                req.setAttribute("msg", "收款登记失败！");
            } else if (payRecord.getPayType() == 2 || payRecord.getPayType() == 4) {
                req.setAttribute("msg", "付款登记失败！");
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, null, null);
        }
    }

    public Page<PoMainVendor> getPoMainCustomers(Page<PoMain> poMainPage, String payType) {
        String[] statuses = new String[2];
        if ("2".equals(payType)) {
            statuses[0] = "1";
            statuses[1] = "1";
        } else if ("3".equals(payType)) {
            statuses[0] = "1";
            statuses[1] = "2";
        } else if ("1".equals(payType)) {
            statuses[0] = "2";
            statuses[1] = "2";
        }
        // 根据销售单付款方式、处理状态查询销售单
        poMainPage = new PoMainDao().getPoMains(poMainPage, payType, statuses);
        // 创建页面显示分页对象
        Page<PoMainVendor> poMainVendorPage = new Page<>();
        List<PoMainVendor> poMainVendors = new ArrayList<>();
        if (poMainPage.getDataList() != null) {
            for (PoMain poMain : poMainPage.getDataList()) {
                PoMainVendor poMainVendor = new PoMainVendor();
                poMainVendor.setPoId(poMain.getPoId());
                poMainVendor.setVendorName(new VendorDao().get(poMain.getVendorCode()).getName());
                poMainVendor.setAccount(poMain.getAccount());
                poMainVendor.setCreateTime(poMain.getCreateTime());
                poMainVendor.setTipFee(poMain.getTipFee());
                poMainVendor.setProductTotal(poMain.getProductTotal());
                poMainVendor.setPoTotal(poMain.getPoTotal());
                poMainVendor.setPayType(poMain.getPayType());
                poMainVendor.setPrePayFee(poMain.getPrePayFee());
                poMainVendor.setStatus(poMain.getStatus());
                poMainVendor.setRemark(poMain.getRemark());
                poMainVendor.setStockTime(poMain.getStockTime());
                poMainVendor.setStockUser(poMain.getStockUser());
                poMainVendor.setPayTime(poMain.getPayTime());
                poMainVendor.setPayUser(poMain.getPayUser());
                poMainVendor.setPrePayTime(poMain.getPrePayTime());
                poMainVendor.setPrePayUser(poMain.getPrePayUser());
                poMainVendor.setEndTime(poMain.getEndTime());
                poMainVendor.setEndUser(poMain.getEndUser());
                poMainVendors.add(poMainVendor);
            }
            poMainVendorPage.setDataList(poMainVendors);
        }
        poMainVendorPage.setCurrentPage(poMainPage.getCurrentPage());
        poMainVendorPage.setPageSize(poMainPage.getPageSize());
        poMainVendorPage.setTotalPage(poMainPage.getTotalPage());
        poMainVendorPage.setTotalRecord(poMainPage.getTotalRecord());
        return poMainVendorPage;
    }

    public List<PoItemProduct> getPoItem(String poId) {
        List<PoItem> poItems = new PoItemDao().get(poId);
        List<PoItemProduct> poItemProducts = new ArrayList<>();
        for (PoItem poItem : poItems) {
            PoItemProduct poItemProduct = new PoItemProduct();
            poItemProduct.setPoId(poItem.getPoId());
            poItemProduct.setProductCode(poItem.getProductCode());
            poItemProduct.setProductName(new ProductDao().get(poItem.getProductCode()).getName());
            poItemProduct.setUnitPrice(poItem.getUnitPrice());
            poItemProduct.setNum(poItem.getNum());
            poItemProduct.setUnitName(poItem.getUnitName());
            poItemProduct.setItemPrice(poItem.getItemPrice());
            poItemProducts.add(poItemProduct);
        }
        return poItemProducts;
    }

    public PoMainVendor getPoMainCustomer(String poId) {
        PoMain poMain = new PoMainDao().getPoMain(poId);
        PoMainVendor poMainVendor = new PoMainVendor();
        poMainVendor.setPoId(poMain.getPoId());
        poMainVendor.setVendorName(new CustomerDao().get(poMain.getVendorCode()).getName());
        poMainVendor.setAccount(poMain.getAccount());
        poMainVendor.setCreateTime(poMain.getCreateTime());
        poMainVendor.setTipFee(poMain.getTipFee());
        poMainVendor.setProductTotal(poMain.getProductTotal());
        poMainVendor.setPoTotal(poMain.getPoTotal());
        poMainVendor.setPayType(poMain.getPayType());
        poMainVendor.setPrePayFee(poMain.getPrePayFee());
        poMainVendor.setStatus(poMain.getStatus());
        poMainVendor.setRemark(poMain.getRemark());
        poMainVendor.setStockTime(poMain.getStockTime());
        poMainVendor.setStockUser(poMain.getStockUser());
        poMainVendor.setPayTime(poMain.getPayTime());
        poMainVendor.setPayUser(poMain.getPayUser());
        poMainVendor.setPrePayTime(poMain.getPrePayTime());
        poMainVendor.setPrePayUser(poMain.getPrePayUser());
        poMainVendor.setEndTime(poMain.getEndTime());
        poMainVendor.setEndUser(poMain.getEndUser());
        return poMainVendor;
    }

    public Page<PayRecordPayType> getPayRecordPayTypes(PayRecordQuery payRecordQuery, Page<PayRecordPayType> payRecordPayTypePage) {
        return new PayRecordDao().get(payRecordQuery, payRecordPayTypePage);
    }
}
