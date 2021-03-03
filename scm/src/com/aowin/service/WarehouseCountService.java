package com.aowin.service;

import com.aowin.dao.CheckStockDao;
import com.aowin.dao.ProductDao;
import com.aowin.model.CheckStock;
import com.aowin.model.Page;
import com.aowin.model.Product;
import com.aowin.model.StockUpdate;
import com.aowin.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 库存盘点Service
 *
 * @author bingo
 * @date 2020/12/3
 */
public class WarehouseCountService {
    public Page<Product> getAllProducts(Page<Product> productPage) {
        return new ProductDao().query(productPage);
    }

    public Page<CheckStock> getAllStocks(Page<CheckStock> checkStockPage) {
        return new CheckStockDao().query(checkStockPage);
    }

    /**
     * 添加盘点记录，修改库存
     *
     * @param stockUpdate
     */
    public void update(StockUpdate stockUpdate) {
        // 封装修改Product对象作为参数
        Product product = new Product();
        product.setProductCode(stockUpdate.getProductCode());
        product.setNum(stockUpdate.getRealNum());
        // 封装修改CheckStock对象作为参数
        CheckStock checkStock = new CheckStock();
        checkStock.setProductCode(stockUpdate.getProductCode());
        checkStock.setOriginNum(stockUpdate.getOriginNum());
        checkStock.setRealNum(stockUpdate.getRealNum());
        checkStock.setStockTime(stockUpdate.getStockTime());
        checkStock.setCreateUser(stockUpdate.getCreateUser());
        checkStock.setDescription(stockUpdate.getDescription());
        checkStock.setType(stockUpdate.getType());
        Connection conn = DBUtil.getConn();
        try {
            conn.setAutoCommit(false);
            // 更新库存
            new ProductDao().alter(product, conn);
            // 添加盘点记录
            new CheckStockDao().insert(checkStock, conn);
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, null, null);
        }
    }
}
