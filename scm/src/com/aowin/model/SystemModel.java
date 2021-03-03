package com.aowin.model;

/**
 * 系统模块表模型
 *
 * @author bingo
 * @date 2020/12/4
 */
public class SystemModel {
    private Integer modeCode;
    private String modelName;
    private String modeUri;

    public Integer getModeCode() {
        return modeCode;
    }

    public void setModeCode(Integer modeCode) {
        this.modeCode = modeCode;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModeUri() {
        return modeUri;
    }

    public void setModeUri(String modeUri) {
        this.modeUri = modeUri;
    }
}
