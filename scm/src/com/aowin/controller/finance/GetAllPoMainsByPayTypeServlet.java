package com.aowin.controller.finance;

import com.aowin.model.Page;
import com.aowin.model.PoMain;
import com.aowin.model.PoMainVendor;
import com.aowin.service.FinanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据采购单付款方式获取采购单
 *
 * @author bingo
 * @date 2020/12/8
 */
@WebServlet("/finance/getAllPoMainsByPayType")
public class GetAllPoMainsByPayTypeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String payType = req.getParameter("payType");
        if (payType == null) {
            payType = "2";
        }
        // 获取销售单表页码，查询分页对象并转发
        String currentPoMainPage = req.getParameter("currentPoMainPage");
        if (currentPoMainPage == null || "0".equals(currentPoMainPage) || currentPoMainPage.length() == 0) {
            currentPoMainPage = "1";
        }
        Page<PoMain> poMainPage = new Page<>();
        poMainPage.setCurrentPage(Integer.parseInt(currentPoMainPage));
        poMainPage.setPageSize(3);
        Page<PoMainVendor> poMainVendorPage = new FinanceService().getPoMainCustomers(poMainPage, payType);
        req.setAttribute("poMainVendorPage", poMainVendorPage);
        req.setAttribute("payType", payType);
        req.getRequestDispatcher("/gztm/finance_pay.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
