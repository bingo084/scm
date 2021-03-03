package com.aowin.controller.scmuser;

import com.aowin.model.Page;
import com.aowin.model.ScmUserModel;
import com.aowin.service.ScmUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取所有scmUser
 *
 * @author bingo
 * @date 2020/12/4
 */
@WebServlet("/scmUser/getAllScmUser")
public class GetAllScmUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentScmUserPage = req.getParameter("currentScmUserPage");
        if (currentScmUserPage == null || "0".equals(currentScmUserPage) || currentScmUserPage.length() == 0) {
            currentScmUserPage = "1";
        }
        Page<ScmUserModel> scmUserModelPage = new Page<>();
        scmUserModelPage.setCurrentPage(Integer.parseInt(currentScmUserPage));
        scmUserModelPage.setPageSize(3);
        scmUserModelPage = new ScmUserService().getALlScmUserModel(scmUserModelPage);
        req.setAttribute("scmUserModelPage", scmUserModelPage);
        req.getRequestDispatcher("/gztm/scmuser.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
