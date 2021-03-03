package com.aowin.dao;

import com.aowin.model.PoItem;
import com.aowin.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 采购单明细表 基本增删改查
 *
 * @author bingo
 */
public class PoItemDao {
    public List<PoItem> get(String poId) {
        List<PoItem> poItems = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql = "select * from poitem where POID=" + poId;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                PoItem poItem = new PoItem();
                poItem.setPoId(rs.getBigDecimal("POID"));
                poItem.setProductCode(rs.getString("ProductCode"));
                poItem.setUnitPrice(rs.getFloat("UnitPrice"));
                poItem.setNum(rs.getInt("Num"));
                poItem.setUnitName(rs.getString("UnitName"));
                poItem.setItemPrice(rs.getFloat("ItemPrice"));
                poItems.add(poItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, st, rs);
        }
        return poItems;
    }
}
