package com.aowin.model;

/**
 * 用于网页显示用户信息的模型
 *
 * @author bingo
 * @date 2020/12/4
 */
public class ScmUserModel extends ScmUser {
    private String modelName;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
