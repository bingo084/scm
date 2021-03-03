package com.aowin.dao;

import com.aowin.model.Customer;
import com.aowin.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 顾客表 基本增删改查
 *
 * @author bingo
 * @date 2020/12/7
 */
public class CustomerDao {
    /**
     * 根据customerCode获得customer
     *
     * @param customerCode 客户编号
     * @return 客户
     */
    public Customer get(String customerCode) {
        Customer customer = new Customer();
        Connection conn = DBUtil.getConn();
        String sql = "select * from customer where CustomerCode=" + customerCode;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                customer.setCustomerCode(rs.getString("CustomerCode"));
                customer.setName(rs.getString("Name"));
                customer.setPassword(rs.getString("Password"));
                customer.setContactor(rs.getString("Contactor"));
                customer.setAddress(rs.getString("Address"));
                customer.setPostCode(rs.getString("Postcode"));
                customer.setTel(rs.getString("Tel"));
                customer.setFax(rs.getString("Fax"));
                customer.setCreateDate(rs.getString("CreateDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, st, rs);
        }
        return customer;
    }
}
