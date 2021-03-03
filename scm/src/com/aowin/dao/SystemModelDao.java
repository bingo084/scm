package com.aowin.dao;

import com.aowin.model.SystemModel;
import com.aowin.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统模块表 基本增删改查
 *
 * @author bingo
 * @date 2020/12/4
 */
public class SystemModelDao {
    public List<SystemModel> get() {
        List<SystemModel> systemModels = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from systemmodel";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SystemModel systemModel = new SystemModel();
                systemModel.setModeCode(rs.getInt("ModelCode"));
                systemModel.setModelName(rs.getString("ModelName"));
                systemModel.setModeUri(rs.getString("ModelUri"));
                systemModels.add(systemModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return systemModels;
    }
}
