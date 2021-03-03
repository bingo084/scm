package com.aowin.dao;

import com.aowin.model.*;
import com.aowin.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 收付款登记表 基本增删改查
 *
 * @author bingo
 * @date 2020/12/8
 */
public class PayRecordDao {
    public void add(Connection conn, PayRecord payRecord) throws SQLException {
        String sql = "insert into payrecord(pay_time,pay_price,account,ordercode,pay_type) values(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, payRecord.getPayTime());
            ps.setBigDecimal(2, payRecord.getPayPrice());
            ps.setString(3, payRecord.getAccount());
            ps.setString(4, payRecord.getOrderCode());
            ps.setInt(5, payRecord.getPayType());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            DBUtil.close(null, ps, null);
        }
    }

    public Page<PayRecordPayType> get(PayRecordQuery payRecordQuery, Page<PayRecordPayType> payRecordPayTypePage) {
        List<PayRecordPayType> payRecordPayTypes = new ArrayList<>();
        StringBuilder sb = new StringBuilder("select * from payrecord ");
        if ("receive".equals(payRecordQuery.getIncomeType())) {
            sb.append("left join pomain on payrecord.ordercode=pomain.POID where 1=1 and (pay_type=1 or pay_type=3) ");
        } else if ("pay".equals(payRecordQuery.getIncomeType())) {
            sb.append("left join somain on payrecord.ordercode=somain.SOID where 1=1 and (pay_type=2 or pay_type=4) ");
        }
        if (payRecordQuery.getOrderCode() != null && payRecordQuery.getOrderCode().length() != 0) {
            sb.append("and ordercode like '%" + payRecordQuery.getOrderCode() + "%' ");
        }
        if (payRecordQuery.getStartDate() != null && payRecordQuery.getStartDate().length() != 0) {
            sb.append("and pay_time>='" + payRecordQuery.getStartDate() + "' ");
        }
        if (payRecordQuery.getEndDate() != null && payRecordQuery.getEndDate().length() != 0) {
            sb.append("and pay_time<='" + payRecordQuery.getEndDate() + " 23:59:59' ");
        }
        if (payRecordQuery.getPayType() != null && payRecordQuery.getPayType().length() != 0) {
            sb.append("and PayType=" + payRecordQuery.getPayType() + " ");
        }
        sb.append("limit ?,?");
        String sql = new String(sb);
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (payRecordPayTypePage.getCurrentPage() - 1) * payRecordPayTypePage.getPageSize());
            ps.setInt(2, payRecordPayTypePage.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                PayRecordPayType payRecordPayType = new PayRecordPayType();
                payRecordPayType.setRecordId(rs.getInt("record_id"));
                payRecordPayType.setPayTime(rs.getString("pay_time"));
                payRecordPayType.setPayPrice(rs.getBigDecimal("pay_price"));
                payRecordPayType.setAccount(rs.getString("account"));
                payRecordPayType.setOrderCode(rs.getString("ordercode"));
                payRecordPayType.setPayType(rs.getInt("pay_type"));
                payRecordPayType.setMainPayType(rs.getInt("PayType"));
                payRecordPayTypes.add(payRecordPayType);
            }
            payRecordPayTypePage.setDataList(payRecordPayTypes);
            int totalRecord = getTotalRecord(conn, ps, rs, sb);
            payRecordPayTypePage.setTotalPage(getTotalPage(totalRecord, payRecordPayTypePage.getPageSize()));
            payRecordPayTypePage.setTotalRecord(totalRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return payRecordPayTypePage;
    }

    /**
     * 获取总记录数
     *
     * @param conn Connection
     * @param ps   Statement
     * @param rs   ResultSet
     * @return 总记录数
     * @throws SQLException 异常
     */
    public int getTotalRecord(Connection conn, PreparedStatement ps, ResultSet rs, StringBuilder sb) throws SQLException {
        String sql = new String(sb.reverse().delete(0, 9).reverse().replace(0, 8, "select count(*) count"));
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }

    /**
     * 获取总页数
     *
     * @param totalRecord 总记录数
     * @param pageSize    每页大小
     * @return 总页数
     */
    public int getTotalPage(int totalRecord, int pageSize) {
        if (totalRecord % pageSize == 0) {
            return totalRecord / pageSize;
        } else {
            return totalRecord / pageSize + 1;
        }
    }

    public List<PayRecord> getByPayTime(String year, String month) {
        List<PayRecord> payRecords = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql = "select * from payrecord where pay_time between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                PayRecord payRecord = new PayRecord();
                payRecord.setPayType(rs.getInt("pay_type"));
                payRecord.setPayPrice(rs.getBigDecimal("pay_price"));
                payRecord.setOrderCode(rs.getString("ordercode"));
                payRecords.add(payRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, st, rs);
        }
        return payRecords;
    }

    public Page<IncomeItem> getByPayTime(String year, String month, String itemType, Page<IncomeItem> incomeItemPage) {
        List<IncomeItem> incomeItems = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql = null;
        if ("receive".equals(itemType)) {
            sql = "select * from payrecord left join somain on payrecord.ordercode=somain.SOID where (pay_type=1 or pay_type=3) and pay_time between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31' limit ?,?";
        } else if ("pay".equals(itemType)) {
            sql = "select * from payrecord left join pomain on payrecord.ordercode=pomain.POID where (pay_type=2 or pay_type=4) and pay_time between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31' limit ?,?";
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (incomeItemPage.getCurrentPage() - 1) * incomeItemPage.getPageSize());
            ps.setInt(2, incomeItemPage.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                IncomeItem incomeItem = new IncomeItem();
                incomeItem.setId(rs.getBigDecimal("ordercode"));
                incomeItem.setCreateTime(rs.getString("CreateTime"));
                incomeItem.setPayTime(rs.getString("pay_time"));
                incomeItem.setPayPrice(rs.getString("pay_price"));
                incomeItem.setAccount(rs.getString("account"));
                incomeItem.setStatus(rs.getInt("Status"));
                incomeItems.add(incomeItem);
            }
            incomeItemPage.setDataList(incomeItems);
            int totalRecord = getTotalRecord(conn, ps, rs, new StringBuilder(sql));
            incomeItemPage.setTotalPage(getTotalPage(totalRecord, incomeItemPage.getPageSize()));
            incomeItemPage.setTotalRecord(totalRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return incomeItemPage;
    }
}
