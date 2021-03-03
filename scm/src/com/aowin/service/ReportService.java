package com.aowin.service;

import com.aowin.dao.PayRecordDao;
import com.aowin.model.IncomeItem;
import com.aowin.model.IncomeMain;
import com.aowin.model.Page;
import com.aowin.model.PayRecord;

import java.math.BigDecimal;
import java.util.List;

/**
 * 报表service
 *
 * @author bingo
 * @date 2020/12/10
 */
public class ReportService {

    public IncomeMain getMain(String year, String month) {
        List<PayRecord> payRecords = new PayRecordDao().getByPayTime(year, month);
        IncomeMain incomeMain = new IncomeMain();
        int payCount = 0;
        BigDecimal paySum = new BigDecimal(0);
        int receiveCount = 0;
        BigDecimal receiveSum = new BigDecimal(0);
        for (PayRecord payRecord : payRecords) {
            if (payRecord.getPayType() == 1 || payRecord.getPayType() == 3) {
                // 收款
                receiveCount++;
                receiveSum = receiveSum.add(payRecord.getPayPrice());
            } else if (payRecord.getPayType() == 2 || payRecord.getPayType() == 4) {
                // 付款
                payCount++;
                paySum = paySum.add(payRecord.getPayPrice());
            }
        }
        incomeMain.setPayCount(payCount);
        incomeMain.setPaySum(paySum);
        incomeMain.setReceiveCount(receiveCount);
        incomeMain.setReceiveSum(receiveSum);
        return incomeMain;
    }

    public Page<IncomeItem> getItem(String year, String month, String itemType, Page<IncomeItem> incomeItemPage) {
        return new PayRecordDao().getByPayTime(year, month, itemType, incomeItemPage);
    }
}
