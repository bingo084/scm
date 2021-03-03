package com.aowin.controller.finance;

import com.aowin.model.PoItemProduct;
import com.aowin.model.PoMainVendor;
import com.aowin.service.FinanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 根据采购单编号获取销售单明细
 *
 * @author bingo
 * @date 2020/12/8
 */
@WebServlet("/finance/getPoItem")
public class GetPoItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String poId = req.getParameter("poId");
        PoMainVendor poMainVendor = new FinanceService().getPoMainCustomer(poId);
        List<PoItemProduct> poItemProducts = new FinanceService().getPoItem(poId);
        req.setAttribute("poMainVendor", poMainVendor);
        req.setAttribute("poItemProducts", poItemProducts);
        req.getRequestDispatcher("/gztm/finance_pay_detail.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
