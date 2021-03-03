package com.aowin.filter;

import com.aowin.model.ScmUser;
import com.aowin.model.SystemModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录过滤器
 *
 * @author bingo
 * @date 2020/12/10
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 假设有用户登录，进行测试
        ScmUser scmUserTest = new ScmUser();
        scmUserTest.setAccount("test");
        scmUserTest.setName("测试");
        scmUserTest.setPassword("test");
        List<SystemModel> systemModelTests = new ArrayList<>();
        SystemModel systemModelTest = new SystemModel();
        systemModelTest.setModeUri("scmUser");
        SystemModel systemModelTest1 = new SystemModel();
        systemModelTest1.setModeUri("finance");
        SystemModel systemModelTest2 = new SystemModel();
        systemModelTest2.setModeUri("warehouse");
        SystemModel systemModelTest3 = new SystemModel();
        systemModelTest3.setModeUri("report");
        systemModelTests.add(systemModelTest);
        systemModelTests.add(systemModelTest1);
        systemModelTests.add(systemModelTest2);
        systemModelTests.add(systemModelTest3);
        scmUserTest.setUserModel(systemModelTests);
        request.getSession().setAttribute("scmUser", scmUserTest);

        ScmUser scmUser = (ScmUser) request.getSession().getAttribute("scmUser");
        if (scmUser == null) {
            request.setAttribute("msg", "您没有登陆账号！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            List<SystemModel> systemModels = scmUser.getUserModel();
            String uri = request.getRequestURI();
            if (uri.contains("css") || uri.contains("images") || uri.contains("script") || uri.contains("login") || uri.contains("jsp")) {
                chain.doFilter(request, response);
            } else if (uri.contains("finance")) {
                for (SystemModel systemModel : systemModels) {
                    if ("finance".equals(systemModel.getModeUri())) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
                request.setAttribute("msg", "您没有权限访问该模块！");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            } else if (uri.contains("warehouse")) {
                for (SystemModel systemModel : systemModels) {
                    if ("warehouse".equals(systemModel.getModeUri())) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
                request.setAttribute("msg", "您没有权限访问该模块！");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            } else if (uri.contains("scmUser")) {
                for (SystemModel systemModel : systemModels) {
                    if ("scmUser".equals(systemModel.getModeUri())) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
                request.setAttribute("msg", "您没有权限访问该模块！");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            } else if (uri.contains("report")) {
                for (SystemModel systemModel : systemModels) {
                    if ("report".equals(systemModel.getModeUri())) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
                request.setAttribute("msg", "您没有权限访问该模块！");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("msg", "您没有权限访问该模块！");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
        }
    }

    @Override
    public void destroy() {
    }
}
