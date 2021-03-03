package com.aowin.dao;

import com.aowin.model.CheckStock;
import com.aowin.model.Page;
import com.aowin.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * checkstock表 基本增删改查
 *
 * @author bingo
 * @date 2020/12/3
 */
public class CheckStockDao {
    /**
     * 增加盘点记录
     *
     * @param checkStock 封装好的增加记录
     * @param conn       开启事务的连接
     */
    public void insert(CheckStock checkStock, Connection conn) throws SQLException {
        String sql = "insert into checkstock(ProductCode,OriginNum,RealNum,StockTime,CreateUser,Description,Type) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, checkStock.getProductCode());
        ps.setInt(2, checkStock.getOriginNum());
        ps.setInt(3, checkStock.getRealNum());
        ps.setString(4, checkStock.getStockTime());
        ps.setString(5, checkStock.getCreateUser());
        ps.setString(6, checkStock.getDescription());
        ps.setString(7, checkStock.getType());
        ps.executeUpdate();
        DBUtil.close(null, ps, null);
    }

    /**
     * 删
     */
    public void delete() {

    }

    /**
     * 改
     *
     * @param checkStock
     */
    public void alter(CheckStock checkStock) {

    }

    /**
     * 查询所有盘点
     *
     * @return 所有产品的list
     */
    public Page<CheckStock> query(Page<CheckStock> checkStockPage) {
        List<CheckStock> checkStocks = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql = "select * from checkstock limit ?,?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (checkStockPage.getCurrentPage() - 1) * checkStockPage.getPageSize());
            ps.setInt(2, checkStockPage.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                CheckStock checkStock = new CheckStock();
                checkStock.setStockId(rs.getInt("StockID"));
                checkStock.setProductCode(rs.getString("ProductCode"));
                checkStock.setOriginNum(rs.getInt("OriginNum"));
                checkStock.setRealNum(rs.getInt("RealNum"));
                checkStock.setStockTime(rs.getString("StockTime"));
                checkStock.setCreateUser(rs.getString("CreateUser"));
                checkStock.setDescription(rs.getString("Description"));
                checkStock.setType(rs.getString("Type"));
                checkStocks.add(checkStock);
            }
            checkStockPage.setDataList(checkStocks);
            checkStockPage.setTotalPage(getTotalPage(getTotalRecord(conn, ps, rs), checkStockPage.getPageSize()));
            checkStockPage.setTotalRecord(getTotalRecord(conn, ps, rs));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return checkStockPage;
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
    public int getTotalRecord(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        ps = conn.prepareStatement("select count(*) count from checkstock");
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
}
