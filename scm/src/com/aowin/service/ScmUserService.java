package com.aowin.service;

import com.aowin.dao.ScmUserDao;
import com.aowin.dao.SystemModelDao;
import com.aowin.dao.UserModelDao;
import com.aowin.model.*;
import com.aowin.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理service
 *
 * @author bingo
 * @date 2020/12/4
 */
public class ScmUserService {
    Logger log = LogManager.getLogger(ScmUserService.class);

    /**
     * 获取所有的scmUser封装为scmUserModel方便显示在页面上
     *
     * @param scmUserModelPage
     * @return List<ScmUserModel>
     */
    public Page<ScmUserModel> getALlScmUserModel(Page<ScmUserModel> scmUserModelPage) {
        // 获取所有scmUser
        scmUserModelPage = new ScmUserDao().get(scmUserModelPage);
        // 获取所有userModel
        List<UserModel> userModels = new UserModelDao().get();
        // 获取所有systemModel
        List<SystemModel> systemModels = new SystemModelDao().get();
        List<ScmUserModel> scmUserModels = new ArrayList<>();
        // 把userModel和systemModel中的modelName赋值给scmUserModel
        for (ScmUserModel scmUserModel : scmUserModelPage.getDataList()) {
            StringBuilder sb = new StringBuilder();
            for (UserModel userModel : userModels) {
                if (userModel.getAccount().equals(scmUserModel.getAccount())) {
                    for (SystemModel systemModel : systemModels) {
                        if (userModel.getModelCode().equals(systemModel.getModeCode())) {
                            sb.append(systemModel.getModelName());
                            sb.append("，");
                        }
                    }
                }
            }
            scmUserModel.setModelName(sb.length() == 0 ? sb.toString() : sb.deleteCharAt(sb.length() - 1).toString());
        }
        return scmUserModelPage;
    }

    /**
     * 根据account删除userModel和scmUser中的用户
     *
     * @param account 要删除的account
     * @param req     设置返回参数
     */
    public void deleteAccount(String account, HttpServletRequest req) {
        Connection conn = DBUtil.getConn();
        try {
            // 开启事务
            conn.setAutoCommit(false);
            // 删除userModel的用户
            new UserModelDao().delete(conn, account);
            // 删除scmUser的用户
            int count = new ScmUserDao().delete(conn, account);
            conn.commit();
            if (count != 0) {
                req.setAttribute("msg", "删除成功！");
            } else {
                req.setAttribute("msg", "用户不存在，删除失败！");
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if (e.getMessage().contains("pomain")) {
                req.setAttribute("msg", "该用户存在对应采购单，无法删除！");
            } else if (e.getMessage().contains("somain")) {
                req.setAttribute("msg", "该用户存在对应销售单，无法删除！");
            } else {
                e.printStackTrace();
                req.setAttribute("msg", "删除失败！");
            }
        } finally {
            DBUtil.close(conn, null, null);
        }
    }

    /**
     * 新增用户
     *
     * @param scmUser    新增scmUser表对象
     * @param userModels 新增userModel表对象
     * @param req        设置返回参数
     */
    public void addScmUserModel(ScmUser scmUser, List<UserModel> userModels, HttpServletRequest req) {
        Connection conn = DBUtil.getConn();
        try {
            // 开启事务
            conn.setAutoCommit(false);
            // 新增scmUser
            new ScmUserDao().add(conn, scmUser);
            // 新增userModel
            for (UserModel userModel : userModels) {
                new UserModelDao().add(conn, userModel);
            }
            conn.commit();
            req.setAttribute("msg", "新增用户成功！");
            log.info("新增用户成功！账号：" + scmUser.getAccount() + " 用户名：" + scmUser.getName());
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            req.setAttribute("msg", "新增用户失败！");
            log.debug("新增用户失败！原因：" + e.getMessage());
        } finally {
            DBUtil.close(conn, null, null);
        }
    }

    /**
     * 修改用户信息
     *
     * @param scmUser    要修改的基本信息
     * @param userModels 要修改的权限
     * @param req        设置返回参数
     */
    public void editScmUserModel(ScmUser scmUser, List<UserModel> userModels, HttpServletRequest req) {
        Connection conn = DBUtil.getConn();
        try {
            // 开启事务
            conn.setAutoCommit(false);
            // 修改scmUser
            new ScmUserDao().edit(conn, scmUser);
            // 删除原有的userModel
            new UserModelDao().delete(conn, scmUser.getAccount());
            // 重新添加权限
            for (UserModel userModel : userModels) {
                new UserModelDao().add(conn, userModel);
            }
            conn.commit();
            req.setAttribute("msg", "用户信息修改成功！");
            log.info("用户信息修改成功！账号：" + scmUser.getAccount() + " 用户名：" + scmUser.getName());
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            req.setAttribute("msg", "用户信息修改失败！");
            log.debug("用户信息修改失败！原因：" + e.getMessage());
        } finally {
            DBUtil.close(conn, null, null);
        }
    }
}