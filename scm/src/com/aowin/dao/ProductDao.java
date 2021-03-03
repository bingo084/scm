package com.aowin.dao;

import com.aowin.model.Page;
import com.aowin.model.Product;
import com.aowin.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * product表 基本增删改查
 *
 * @author bingo
 * @date 2020/12/3
 */
public class ProductDao {
    /**
     * 增
     *
     * @param product
     */
    public void insert(Product product) {

    }

    /**
     * 删
     */
    public void delete() {

    }

    /**
     * 修改产品库存
     *
     * @param product 封装好的修改产品
     * @param conn    开启事务的连接
     */
    public void alter(Product product, Connection conn) throws SQLException {
        String sql = "update product set num=? where ProductCode=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, product.getNum());
            ps.setString(2, product.getProductCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            DBUtil.close(null, ps, null);
        }
    }

    public Product get(String productCode) {
        Product product = new Product();
        Connection conn = DBUtil.getConn();
        String sql = "select * from product where ProductCode=" + productCode;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                product.setName(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, st, rs);
        }
        return product;
    }

    /**
     * 查询所有产品
     *
     * @return 所有产品的list
     */
    public Page<Product> query(Page<Product> productPage) {
        List<Product> products = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql = "select * from product limit ?,?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (productPage.getCurrentPage() - 1) * productPage.getPageSize());
            ps.setInt(2, productPage.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductCode(rs.getString("ProductCode"));
                product.setCategoryId(rs.getInt("CategoryID"));
                product.setName(rs.getString("Name"));
                product.setUnitName(rs.getString("UnitName"));
                product.setPrice(rs.getFloat("Price"));
                product.setCreateDate(rs.getString("CreateDate"));
                product.setRemark(rs.getString("Remark"));
                product.setNum(rs.getInt("num"));
                product.setPoNum(rs.getInt("PONum"));
                product.setSoNum(rs.getInt("SONum"));
                products.add(product);
            }
            productPage.setDataList(products);
            productPage.setTotalPage(getTotalPage(getTotalRecord(conn, ps, rs), productPage.getPageSize()));
            productPage.setTotalRecord(getTotalRecord(conn, ps, rs));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return productPage;
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
        ps = conn.prepareStatement("select count(*) count from product");
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
