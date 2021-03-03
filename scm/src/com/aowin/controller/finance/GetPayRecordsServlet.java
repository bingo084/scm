package com.aowin.controller.finance;

import com.aowin.model.Page;
import com.aowin.model.PayRecordPayType;
import com.aowin.model.PayRecordQuery;
import com.aowin.service.FinanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据查询条件查询收支记录
 *
 * @author bingo
 * @date 2020/12/9
 */
@WebServlet("/finance/getPayRecords")
public class GetPayRecordsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String incomeType = req.getParameter("incomeType");
        if (incomeType != null && incomeType.length() != 0) {
            PayRecordQuery payRecordQuery = new PayRecordQuery();
            payRecordQuery.setStartDate(req.getParameter("startDate"));
            payRecordQuery.setEndDate(req.getParameter("endDate"));
            payRecordQuery.setIncomeType(incomeType);
            payRecordQuery.setPayType(req.getParameter("payType"));
            payRecordQuery.setOrderCode(req.getParameter("orderCode"));
            String currentPage = req.getParameter("currentPage");
            if (currentPage == null || "0".equals(currentPage) || currentPage.length() == 0) {
                currentPage = "1";
            }
            Page<PayRecordPayType> payRecordPayTypePage = new Page<>();
            payRecordPayTypePage.setCurrentPage(Integer.parseInt(currentPage));
            payRecordPayTypePage.setPageSize(3);
            payRecordPayTypePage = new FinanceService().getPayRecordPayTypes(payRecordQuery, payRecordPayTypePage);
            req.setAttribute("payRecordPayTypePage", payRecordPayTypePage);
            req.setAttribute("startDate", req.getParameter("startDate"));
            req.setAttribute("endDate", req.getParameter("endDate"));
            req.setAttribute("incomeType", req.getParameter("incomeType"));
            req.setAttribute("payType", req.getParameter("payType"));
            req.setAttribute("orderCode", req.getParameter("orderCode"));
            req.setAttribute("payRecordQuery", payRecordQuery);
        }
        req.getRequestDispatcher("/gztm/finance_query.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
