package com.aowin.dao;

import com.aowin.model.UserModel;
import com.aowin.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户模块表 基本增删改查
 *
 * @author bingo
 * @date 2020/12/4
 */
public class UserModelDao {
    /**
     * 新增用户权限
     *
     * @param conn      开启事务的连接
     * @param userModel 要新增的权限
     * @throws SQLException 新增失败抛出异常
     */
    public void add(Connection conn, UserModel userModel) throws SQLException {
        String sql = "insert into usermodel values(?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userModel.getAccount());
            ps.setInt(2, userModel.getModelCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, ps, null);
        }
    }

    /**
     * 删
     *
     * @param account 要删除的账号
     * @param conn    开启事务的连接
     */
    public int delete(Connection conn, String account) throws SQLException {
        String sql = "delete from usermodel where Account='" + account + "'";
        Statement st = null;
        int count = 0;
        try {
            st = conn.createStatement();
            count = st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, st, null);
        }
        return count;
    }

    /**
     * 查
     *
     * @return userModel的list
     */
    public List<UserModel> get() {
        List<UserModel> userModels = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from usermodel";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel userModel = new UserModel();
                userModel.setAccount(rs.getString("Account"));
                userModel.setModelCode(rs.getInt("ModelCode"));
                userModels.add(userModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return userModels;
    }
}
