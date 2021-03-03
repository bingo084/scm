package com.aowin.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 连接、关闭数据库工具类
 *
 * @author bingo
 * @date 2020/12/3
 */
public class DBUtil {
    /**
     * 获取连接
     *
     * @return 连接
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            InitialContext init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
            conn = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     *
     * @param conn connection
     * @param st   statement
     * @param rs   resultSet
     */
    public static void close(Connection conn, Statement st, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("资源关闭失败：" + e.getMessage());
        }
    }
}
