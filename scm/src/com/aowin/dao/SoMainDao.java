package com.aowin.dao;

import com.aowin.model.Page;
import com.aowin.model.PayRecord;
import com.aowin.model.SoMain;
import com.aowin.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * soMain表 基本增删改查
 *
 * @author bingo
 */
public class SoMainDao {

    /**
     * 根据soId查询销售主单
     *
     * @param soId 销售单编号
     * @return 销售主单对象
     */
    public SoMain getSoMain(String soId) {
        SoMain soMain = new SoMain();
        Connection conn = DBUtil.getConn();
        String sql = "select * from somain where SOID=" + soId;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                soMain.setSoId(rs.getBigDecimal("SOID"));
                soMain.setCustomerCode(rs.getString("CustomerCode"));
                soMain.setAccount(rs.getString("Account"));
                soMain.setCreateTime(rs.getString("CreateTime"));
                soMain.setTipFee(rs.getFloat("TipFee"));
                soMain.setProductTotal(rs.getFloat("ProductTotal"));
                soMain.setPoTotal(rs.getFloat("POTotal"));
                soMain.setPayType(rs.getInt("PayType"));
                soMain.setPrePayFee(rs.getFloat("PrePayFee"));
                soMain.setStatus(rs.getInt("Status"));
                soMain.setRemark(rs.getString("Remark"));
                soMain.setStockTime(rs.getString("StockTime"));
                soMain.setStockUser(rs.getString("StockUser"));
                soMain.setPayTime(rs.getString("PayTime"));
                soMain.setPayUser(rs.getString("PayUser"));
                soMain.setPrePayTime(rs.getString("PrePayTime"));
                soMain.setPrePayUser(rs.getString("PrePayUser"));
                soMain.setEndTime(rs.getString("EndTime"));
                soMain.setEndUser(rs.getString("EndUser"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, st, rs);
        }
        return soMain;
    }

    /**
     * 获取所有销售主单
     *
     * @param soMainPage 分页对象
     * @param payType    付款方式
     * @param statuses   状态信息
     * @return 销售主单的分页对象
     */
    public Page<SoMain> getSoMains(Page<SoMain> soMainPage, String payType, String[] statuses) {
        Connection conn = DBUtil.getConn();
        List<SoMain> soMains = new ArrayList<>();
        String sql = "select * from somain where payType=? and (status=? or status=?) limit ?,?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, payType);
            ps.setString(2, statuses[0]);
            ps.setString(3, statuses[1]);
            ps.setInt(4, (soMainPage.getCurrentPage() - 1) * soMainPage.getPageSize());
            ps.setInt(5, soMainPage.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                SoMain soMain = new SoMain();
                soMain.setSoId(rs.getBigDecimal("SOID"));
                soMain.setCustomerCode(rs.getString("CustomerCode"));
                soMain.setAccount(rs.getString("Account"));
                soMain.setCreateTime(rs.getString("CreateTime"));
                soMain.setTipFee(rs.getFloat("TipFee"));
                soMain.setProductTotal(rs.getFloat("ProductTotal"));
                soMain.setPoTotal(rs.getFloat("POTotal"));
                soMain.setPayType(rs.getInt("PayType"));
                soMain.setPrePayFee(rs.getFloat("PrePayFee"));
                soMain.setStatus(rs.getInt("Status"));
                soMain.setRemark(rs.getString("Remark"));
                soMain.setStockTime(rs.getString("StockTime"));
                soMain.setStockUser(rs.getString("StockUser"));
                soMain.setPayTime(rs.getString("PayTime"));
                soMain.setPayUser(rs.getString("PayUser"));
                soMain.setPrePayTime(rs.getString("PrePayTime"));
                soMain.setPrePayUser(rs.getString("PrePayUser"));
                soMain.setEndTime(rs.getString("EndTime"));
                soMain.setEndUser(rs.getString("EndUser"));
                soMains.add(soMain);
            }
            soMainPage.setDataList(soMains);
            soMainPage.setTotalPage(getTotalPage(getTotalRecord(conn, ps, rs, payType, statuses), soMainPage.getPageSize()));
            soMainPage.setTotalRecord(getTotalRecord(conn, ps, rs, payType, statuses));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return soMainPage;
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
        ps = conn.prepareStatement("select count(*) count from somain where payType=? and (status=? or status=?)");
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
        if (payRecord.getPayType() == 1) {
            sql = "update somain set Status=3,PayTime=?,PayUser=? where SOID=?";
        } else if (payRecord.getPayType() == 3) {
            sql = "update somain set Status=5,PrePayTime=?,PrePayUser=? where SOID=?";
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
