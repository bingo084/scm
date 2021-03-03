package com.aowin.controller.finance;

import com.aowin.model.SoItemProduct;
import com.aowin.model.SoMainCustomer;
import com.aowin.service.FinanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 根据销售单编号获取销售单明细
 *
 * @author bingo
 * @date 2020/12/8
 */
@WebServlet("/finance/getSoItem")
public class GetSoItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String soId = req.getParameter("soId");
        SoMainCustomer soMainCustomer = new FinanceService().getSoMainCustomer(soId);
        List<SoItemProduct> soItemProducts = new FinanceService().getSoItem(soId);
        req.setAttribute("soMainCustomer", soMainCustomer);
        req.setAttribute("soItemProducts", soItemProducts);
        req.getRequestDispatcher("/gztm/finance_receive_detail.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
