package com.aowin.controller.warehouse;

import com.aowin.model.CheckStock;
import com.aowin.model.Page;
import com.aowin.model.Product;
import com.aowin.service.WarehouseCountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回库存更新主界面所有产品列表
 *
 * @author bingo
 */
@WebServlet("/warehouse/getAll")
public class GetAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取当前产品表页码，查询对应分页对象并转发
        String currentProductPage = req.getParameter("currentProductPage");
        if (currentProductPage == null) {
            currentProductPage = "1";
        }
        Page<Product> productPage = new Page<>();
        productPage.setCurrentPage(Integer.parseInt(currentProductPage));
        productPage.setPageSize(3);
        productPage = new WarehouseCountService().getAllProducts(productPage);
        req.setAttribute("productPage", productPage);
        // 获取当前盘点表页码，查询对应分页对象并转发
        String currentStockPage = req.getParameter("currentStockPage");
        if (currentStockPage == null || "0".equals(currentStockPage) || currentStockPage.length() == 0) {
            currentStockPage = "1";
        }
        Page<CheckStock> checkStockPage = new Page<>();
        checkStockPage.setCurrentPage(Integer.parseInt(currentStockPage));
        checkStockPage.setPageSize(3);
        checkStockPage = new WarehouseCountService().getAllStocks(checkStockPage);
        req.setAttribute("checkStockPage", checkStockPage);
        req.getRequestDispatcher("/gztm/warehouse_count.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
