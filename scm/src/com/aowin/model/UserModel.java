package com.aowin.model;

/**
 * 用户模块表模型
 *
 * @author bingo
 * @date 2020/12/4
 */
public class UserModel {
    private String account;
    private Integer modelCode;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getModelCode() {
        return modelCode;
    }

    public void setModelCode(Integer modeCode) {
        this.modelCode = modeCode;
    }
}
