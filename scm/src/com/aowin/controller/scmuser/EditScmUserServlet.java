package com.aowin.controller.scmuser;

import com.aowin.model.ScmUser;
import com.aowin.model.UserModel;
import com.aowin.service.ScmUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改scmUser信息
 *
 * @author bingo
 * @date 2020/12/4
 */
@WebServlet("/scmUser/editScmUser")
public class EditScmUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String createDate = req.getParameter("createDate");
        String status = req.getParameter("status");
        String[] authorities = req.getParameterValues("authority");
        List<UserModel> userModels = new ArrayList<>();
        if (authorities != null) {
            // 封装为userModel的List
            for (String authority : authorities) {
                UserModel userModel = new UserModel();
                userModel.setAccount(account);
                userModel.setModelCode(Integer.parseInt(authority));
                userModels.add(userModel);
            }
        }
        // 封装为ScmUser对象
        ScmUser scmUser = new ScmUser();
        scmUser.setAccount(account);
        scmUser.setName(name);
        scmUser.setPassword(password);
        scmUser.setStatus(Integer.parseInt(status));
        scmUser.setCreateDate(createDate);
        new ScmUserService().editScmUserModel(scmUser, userModels, req);
        req.getRequestDispatcher("/scmUser/getAllScmUser").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
