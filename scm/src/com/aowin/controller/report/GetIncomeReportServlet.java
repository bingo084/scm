package com.aowin.controller.report;

import com.aowin.model.IncomeItem;
import com.aowin.model.IncomeMain;
import com.aowin.model.Page;
import com.aowin.service.ReportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bingo
 */
@WebServlet("/report/getIncomeReport")
public class GetIncomeReportServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String year = req.getParameter("year");
        String month = req.getParameter("month");
        if (year != null && year.length() != 0 && month != null && month.length() != 0) {
            String itemType = req.getParameter("itemType");
            if (itemType == null) {
                itemType = "receive";
            }
            String currentPage = req.getParameter("currentPage");
            if (currentPage == null || "0".equals(currentPage) || currentPage.length() == 0) {
                currentPage = "1";
            }
            Page<IncomeItem> incomeItemPage = new Page<>();
            incomeItemPage.setCurrentPage(Integer.parseInt(currentPage));
            incomeItemPage.setPageSize(3);
            IncomeMain incomeMain = new ReportService().getMain(year, month);
            incomeItemPage = new ReportService().getItem(year, month, itemType, incomeItemPage);
            req.setAttribute("year", year);
            req.setAttribute("month", month);
            req.setAttribute("itemType", itemType);
            req.setAttribute("incomeMain", incomeMain);
            req.setAttribute("incomeItemPage", incomeItemPage);
        }
        req.getRequestDispatcher("/gztm/report_income.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
