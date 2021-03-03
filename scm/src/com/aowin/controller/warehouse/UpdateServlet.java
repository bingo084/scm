package com.aowin.controller.warehouse;

import com.aowin.model.ScmUser;
import com.aowin.model.StockUpdate;
import com.aowin.service.WarehouseCountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 更新库存盘点servlet
 *
 * @author bingo
 * @date 2020/12/4
 */
@WebServlet("/warehouse/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        String productCode = req.getParameter("productCode");
        String name = req.getParameter("name");
        String originNum = req.getParameter("originNum");
        String realNum = req.getParameter("realNum");
        String type = req.getParameter("type");
        String reason = req.getParameter("reason");
        // 封装对象
        StockUpdate stockUpdate = new StockUpdate();
        stockUpdate.setProductCode(productCode);
        stockUpdate.setProductName(name);
        stockUpdate.setOriginNum(Integer.valueOf(originNum));
        stockUpdate.setRealNum(Integer.valueOf(realNum));
        stockUpdate.setType(type);
        stockUpdate.setDescription(reason);
        stockUpdate.setStockTime(new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
        // 暂时写死
        ScmUser scmUser = (ScmUser) req.getSession().getAttribute("scmUser");
        stockUpdate.setCreateUser(scmUser.getAccount());
        new WarehouseCountService().update(stockUpdate);
        resp.sendRedirect(req.getContextPath() + "/warehouse/getAll");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
