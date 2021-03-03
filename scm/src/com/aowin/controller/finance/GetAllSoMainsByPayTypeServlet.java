package com.aowin.controller.finance;

import com.aowin.model.Page;
import com.aowin.model.SoMain;
import com.aowin.model.SoMainCustomer;
import com.aowin.service.FinanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据销售单付款方式获取销售单
 *
 * @author bingo
 * @date 2020/12/7
 */
@WebServlet("/finance/getAllSoMainsByPayType")
public class GetAllSoMainsByPayTypeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String payType = req.getParameter("payType");
        String[] statuses = new String[2];
        if (payType == null) {
            payType = "2";
        }
        if ("2".equals(payType)) {
            statuses[0] = "1";
            statuses[1] = "1";
        } else if ("3".equals(payType)) {
            statuses[0] = "1";
            statuses[1] = "2";
        } else if ("1".equals(payType)) {
            statuses[0] = "2";
            statuses[1] = "2";
        }
        // 获取销售单表页码，查询分页对象并转发
        String currentSoMainPage = req.getParameter("currentSoMainPage");
        if (currentSoMainPage == null || "0".equals(currentSoMainPage) || currentSoMainPage.length() == 0) {
            currentSoMainPage = "1";
        }
        Page<SoMain> soMainPage = new Page<>();
        soMainPage.setCurrentPage(Integer.parseInt(currentSoMainPage));
        soMainPage.setPageSize(3);
        Page<SoMainCustomer> soMainCustomerPage = null;
        if (payType != null && statuses.length != 0) {
            soMainCustomerPage = new FinanceService().getSoMainCustomers(soMainPage, payType, statuses);
        }
        req.setAttribute("soMainCustomerPage", soMainCustomerPage);
        req.setAttribute("payType", payType);
        req.getRequestDispatcher("/gztm/finance_receive.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
