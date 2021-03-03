package com.aowin.model;

import java.util.List;

/**
 * 用户表模型
 *
 * @author bingo
 * @date 2020/12/4
 */
public class ScmUser {
    private String account;
    private String password;
    private String name;
    private String createDate;
    private Integer status;
    private List<SystemModel> userModel;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String creatDate) {
        this.createDate = creatDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SystemModel> getUserModel() {
        return userModel;
    }

    public void setUserModel(List<SystemModel> userModel) {
        this.userModel = userModel;
    }
}
