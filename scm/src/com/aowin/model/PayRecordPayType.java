package com.aowin.model;

/**
 * 收支查询显示的model
 *
 * @author bingo
 * @date 2020/12/9
 */
public class PayRecordPayType extends PayRecord {
    private Integer mainPayType;

    public Integer getMainPayType() {
        return mainPayType;
    }

    public void setMainPayType(Integer mainPayType) {
        this.mainPayType = mainPayType;
    }
}
