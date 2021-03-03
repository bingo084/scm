package com.aowin.dao;

import com.aowin.model.Vendor;
import com.aowin.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 供应商表 基本增删改查
 *
 * @author bingo
 * @date 2020/12/11
 */
public class VendorDao {
    /**
     * 根据vendorCode获得vendor
     *
     * @param vendorCode 客户编号
     * @return 客户
     */
    public Vendor get(String vendorCode) {
        Vendor vendor = new Vendor();
        Connection conn = DBUtil.getConn();
        String sql = "select * from vender where VenderCode=" + vendorCode;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                vendor.setVendorCode(rs.getString("VenderCode"));
                vendor.setName(rs.getString("Name"));
                vendor.setPassword(rs.getString("Password"));
                vendor.setContactor(rs.getString("Contactor"));
                vendor.setAddress(rs.getString("Address"));
                vendor.setPostCode(rs.getString("Postcode"));
                vendor.setTel(rs.getString("Tel"));
                vendor.setFax(rs.getString("Fax"));
                vendor.setCreateDate(rs.getString("CreateDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, st, rs);
        }
        return vendor;
    }
}
