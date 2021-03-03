package com.aowin.controller.scmuser;

import com.aowin.service.ScmUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除scmUser
 *
 * @author bingo
 * @date 2020/12/4
 */
@WebServlet("/scmUser/deleteScmUser")
public class DeleteScmUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        if (account != null) {
            new ScmUserService().deleteAccount(account, req);
        }
        req.getRequestDispatcher("/scmUser/getAllScmUser").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
