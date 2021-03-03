package com.aowin.dao;

import com.aowin.model.Page;
import com.aowin.model.ScmUser;
import com.aowin.model.ScmUserModel;
import com.aowin.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * scmUser表 基本增删改查
 *
 * @author bingo
 */
public class ScmUserDao {
    /**
     * 新增scmUser
     *
     * @param conn    开启事务的连接
     * @param scmUser 新增的scmUser
     * @throws SQLException 新增失败抛出异常
     */
    public void add(Connection conn, ScmUser scmUser) throws SQLException {
        String sql = "insert into scmuser values(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, scmUser.getAccount());
            ps.setString(2, scmUser.getPassword());
            ps.setString(3, scmUser.getName());
            ps.setString(4, scmUser.getCreateDate());
            ps.setInt(5, scmUser.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            DBUtil.close(null, ps, null);
        }
    }

    /**
     * 根据账户删除用户
     *
     * @param account 要删除的账户
     * @param conn    开启事务的连接
     */
    public int delete(Connection conn, String account) throws SQLException {
        String sql = "delete from scmuser where Account='" + account + "'";
        Statement st = null;
        int count = 0;
        try {
            st = conn.createStatement();
            count = st.executeUpdate(sql);
        } catch (SQLException e) {
            throw e;
        } finally {
            DBUtil.close(null, st, null);
        }
        return count;
    }

    /**
     * 修改账户
     *
     * @param conn    开启事务的连接
     * @param scmUser 要修改的账户
     * @throws SQLException 修改失败抛出异常
     */
    public void edit(Connection conn, ScmUser scmUser) throws SQLException {
        String sql = "update scmuser set Password=?,Name=?,CreateDate=?,Status=? where Account=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, scmUser.getPassword());
            ps.setString(2, scmUser.getName());
            ps.setString(3, scmUser.getCreateDate());
            ps.setInt(4, scmUser.getStatus());
            ps.setString(5, scmUser.getAccount());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            DBUtil.close(null, ps, null);
        }
    }

    /**
     * 查
     *
     * @return scmUser的list
     */
    public List<ScmUser> get() {
        List<ScmUser> scmUsers = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from scmuser";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ScmUser scmUser = new ScmUser();
                scmUser.setAccount(rs.getString("Account"));
                scmUser.setPassword(rs.getString("Password"));
                scmUser.setName(rs.getString("Name"));
                scmUser.setCreateDate(rs.getString("CreateDate"));
                scmUser.setStatus(rs.getInt("Status"));
                scmUsers.add(scmUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return scmUsers;
    }

    /**
     * 分页查询
     *
     * @return scmUser的list
     */
    public Page<ScmUserModel> get(Page<ScmUserModel> scmUserModelPage) {
        List<ScmUserModel> scmUserModels = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from scmuser limit ?,?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (scmUserModelPage.getCurrentPage() - 1) * scmUserModelPage.getPageSize());
            ps.setInt(2, scmUserModelPage.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                ScmUserModel scmUserModel = new ScmUserModel();
                scmUserModel.setAccount(rs.getString("Account"));
                scmUserModel.setPassword(rs.getString("Password"));
                scmUserModel.setName(rs.getString("Name"));
                scmUserModel.setCreateDate(rs.getString("CreateDate"));
                scmUserModel.setStatus(rs.getInt("Status"));
                scmUserModels.add(scmUserModel);
            }
            scmUserModelPage.setDataList(scmUserModels);
            scmUserModelPage.setTotalPage(getTotalPage(getTotalRecord(conn, ps, rs), scmUserModelPage.getPageSize()));
            scmUserModelPage.setTotalRecord(getTotalRecord(conn, ps, rs));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return scmUserModelPage;
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
        ps = conn.prepareStatement("select count(*) count from scmuser");
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
