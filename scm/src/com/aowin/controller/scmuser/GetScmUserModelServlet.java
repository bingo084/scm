package com.aowin.controller.scmuser;

import com.alibaba.fastjson.JSONObject;
import com.aowin.dao.ScmUserDao;
import com.aowin.dao.UserModelDao;
import com.aowin.model.ScmUser;
import com.aowin.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据账号获取对应的scmUser和userModel对象
 *
 * @author bingo
 * @date 2020/12/6
 */
@WebServlet("/scmUser/getScmUserModel")
public class GetScmUserModelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        ScmUser scmUser = new ScmUser();
        List<ScmUser> scmUsers = new ScmUserDao().get();
        for (ScmUser user : scmUsers) {
            if (user.getAccount().equals(account)) {
                scmUser = user;
                break;
            }
        }
        List<UserModel> userModels = new ArrayList<>();
        List<UserModel> userModels1 = new UserModelDao().get();
        for (UserModel userModel : userModels1) {
            if (userModel.getAccount().equals(account)) {
                userModels.add(userModel);
            }
        }
        Object[] objs = {scmUser, userModels};
        String s = JSONObject.toJSONString(objs);
        resp.getWriter().print(s);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
