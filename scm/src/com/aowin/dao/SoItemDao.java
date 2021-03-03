package com.aowin.dao;

import com.aowin.model.SoItem;
import com.aowin.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 销售单明细表 基本增删改查
 *
 * @author bingo
 */
public class SoItemDao {
    public List<SoItem> get(String soId) {
        List<SoItem> soItems = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql = "select * from soitem where SOID=" + soId;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                SoItem soItem = new SoItem();
                soItem.setSoId(rs.getBigDecimal("SOID"));
                soItem.setProductCode(rs.getString("ProductCode"));
                soItem.setUnitPrice(rs.getFloat("UnitPrice"));
                soItem.setNum(rs.getInt("Num"));
                soItem.setUnitName(rs.getString("UnitName"));
                soItem.setItemPrice(rs.getFloat("ItemPrice"));
                soItems.add(soItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, st, rs);
        }
        return soItems;
    }
}
