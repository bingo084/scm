package com.aowin.controller.finance;

import com.aowin.model.PayRecord;
import com.aowin.model.ScmUser;
import com.aowin.service.FinanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 增加收付款记录
 *
 * @author bingo
 * @date 2020/12/8
 */
@WebServlet("/finance/addPayRecord")
public class AddPayRecordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String payTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String payPrice = req.getParameter("pay_price");
        ScmUser scmUser = (ScmUser) req.getSession().getAttribute("scmUser");
        String account = scmUser.getAccount();
        String orderCode = req.getParameter("orderCode");
        String payType = req.getParameter("pay_type");
        PayRecord payRecord = new PayRecord();
        payRecord.setPayTime(payTime);
        payRecord.setPayPrice(new BigDecimal(payPrice));
        payRecord.setAccount(account);
        payRecord.setOrderCode(orderCode);
        payRecord.setPayType(Integer.parseInt(payType));
        new FinanceService().addPayRecord(payRecord, req);
        if (payRecord.getPayType() == 1 || payRecord.getPayType() == 3) {
            req.getRequestDispatcher("/finance/getAllSoMainsByPayType").forward(req, resp);
        } else if (payRecord.getPayType() == 2 || payRecord.getPayType() == 4) {
            req.getRequestDispatcher("/finance/getAllPoMainsByPayType").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
