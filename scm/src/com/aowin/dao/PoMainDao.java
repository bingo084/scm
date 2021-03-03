package com.aowin.dao;

import com.aowin.model.Page;
import com.aowin.model.PayRecord;
import com.aowin.model.PoMain;
import com.aowin.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * poMain表 基本增删改查
 *
 * @author bingo
 */
public class PoMainDao {

    /**
     * 根据poId查询销售主单
     *
     * @param poId 销售单编号
     * @return 销售主单对象
     */
    public PoMain getPoMain(String poId) {
        PoMain poMain = new PoMain();
        Connection conn = DBUtil.getConn();
        String sql = "select * from pomain where POID=" + poId;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                poMain.setPoId(rs.getBigDecimal("POID"));
                poMain.setVendorCode(rs.getString("VenderCode"));
                poMain.setAccount(rs.getString("Account"));
                poMain.setCreateTime(rs.getString("CreateTime"));
                poMain.setTipFee(rs.getFloat("TipFee"));
                poMain.setProductTotal(rs.getFloat("ProductTotal"));
                poMain.setPoTotal(rs.getFloat("POTotal"));
                poMain.setPayType(rs.getInt("PayType"));
                poMain.setPrePayFee(rs.getFloat("PrePayFee"));
                poMain.setStatus(rs.getInt("Status"));
                poMain.setRemark(rs.getString("Remark"));
                poMain.setStockTime(rs.getString("StockTime"));
                poMain.setStockUser(rs.getString("StockUser"));
                poMain.setPayTime(rs.getString("PayTime"));
                poMain.setPayUser(rs.getString("PayUser"));
                poMain.setPrePayTime(rs.getString("PrePayTime"));
                poMain.setPrePayUser(rs.getString("PrePayUser"));
                poMain.setEndTime(rs.getString("EndTime"));
                poMain.setEndUser(rs.getString("EndUser"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, st, rs);
        }
        return poMain;
    }

    /**
     * 获取所有销售主单
     *
     * @param poMainPage 分页对象
     * @param payType    付款方式
     * @param statuses   状态信息
     * @return 销售主单的分页对象
     */
    public Page<PoMain> getPoMains(Page<PoMain> poMainPage, String payType, String[] statuses) {
        Connection conn = DBUtil.getConn();
        List<PoMain> poMains = new ArrayList<>();
        String sql = "select * from pomain where payType=? and (status=? or status=?) limit ?,?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, payType);
            ps.setString(2, statuses[0]);
            ps.setString(3, statuses[1]);
            ps.setInt(4, (poMainPage.getCurrentPage() - 1) * poMainPage.getPageSize());
            ps.setInt(5, poMainPage.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                PoMain poMain = new PoMain();
                poMain.setPoId(rs.getBigDecimal("POID"));
                poMain.setVendorCode(rs.getString("VenderCode"));
                poMain.setAccount(rs.getString("Account"));
                poMain.setCreateTime(rs.getString("CreateTime"));
                poMain.setTipFee(rs.getFloat("TipFee"));
                poMain.setProductTotal(rs.getFloat("ProductTotal"));
                poMain.setPoTotal(rs.getFloat("POTotal"));
                poMain.setPayType(rs.getInt("PayType"));
                poMain.setPrePayFee(rs.getFloat("PrePayFee"));
                poMain.setStatus(rs.getInt("Status"));
                poMain.setRemark(rs.getString("Remark"));
                poMain.setStockTime(rs.getString("StockTime"));
                poMain.setStockUser(rs.getString("StockUser"));
                poMain.setPayTime(rs.getString("PayTime"));
                poMain.setPayUser(rs.getString("PayUser"));
                poMain.setPrePayTime(rs.getString("PrePayTime"));
                poMain.setPrePayUser(rs.getString("PrePayUser"));
                poMain.setEndTime(rs.getString("EndTime"));
                poMain.setEndUser(rs.getString("EndUser"));
                poMains.add(poMain);
            }
            poMainPage.setDataList(poMains);
            poMainPage.setTotalPage(getTotalPage(getTotalRecord(conn, ps, rs, payType, statuses), poMainPage.getPageSize()));
            poMainPage.setTotalRecord(getTotalRecord(conn, ps, rs, payType, statuses));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return poMainPage;
    }

    /**
     * 获取总记录数
     *
     * @param conn Connection
     * @param ps   PreparedStatement
     * @param rs   ResultSet
     * @return 总记录数
     * @throws SQLException 异常
     */
    public int getTotalRecord(Connection conn, PreparedStatement ps, ResultSet rs, String payType, String[] statuses) throws SQLException {
        ps = conn.prepareStatement("select count(*) count from pomain where payType=? and (status=? or status=?)");
        ps.setString(1, payType);
        ps.setString(2, statuses[0]);
        ps.setString(3, statuses[1]);
        rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }

    /**
     * 获取总页数
     *
     * @param totalRecord 总记录数
     * @param pageSize    每页大小
     * @return 总页数
     */
    public int getTotalPage(int totalRecord, int pageSize) {
        if (totalRecord % pageSize == 0) {
            return totalRecord / pageSize;
        } else {
            return totalRecord / pageSize + 1;
        }
    }

    public void edit(Connection conn, PayRecord payRecord) throws SQLException {
        String sql = null;
        PreparedStatement ps = null;
        if (payRecord.getPayType() == 2) {
            sql = "update pomain set Status=3,PayTime=?,PayUser=? where POID=?";
        } else if (payRecord.getPayType() == 4) {
            sql = "update pomain set Status=5,PrePayTime=?,PrePayUser=? where POID=?";
        }
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, payRecord.getPayTime());
            ps.setString(2, payRecord.getAccount());
            ps.setBigDecimal(3, new BigDecimal(payRecord.getOrderCode()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            DBUtil.close(null, ps, null);
        }
    }
}
