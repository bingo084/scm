package com.aowin.controller.scmuser;

import com.aowin.dao.ScmUserDao;
import com.aowin.model.ScmUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 验证注册账号是否存在
 *
 * @author bingo
 * @date 2020/12/6
 */
@WebServlet("/scmUser/checkExist")
public class CheckExistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        boolean flag = false;
        if (account != null && account.length() != 0) {
            List<ScmUser> scmUsers = new ScmUserDao().get();
            for (ScmUser scmUser : scmUsers) {
                if (account.equals(scmUser.getAccount())) {
                    flag = true;
                    break;
                }
            }
            resp.getWriter().print(flag);
        } else {
            resp.getWriter().print("empty");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
