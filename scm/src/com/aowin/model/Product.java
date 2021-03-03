package com.aowin.model;

/**
 * 产品表模型
 *
 * @author bingo
 * @date 2020/12/3
 */
public class Product {
    /**
     * 产品编号
     */
    private String productCode;
    /**
     * 类别序列号
     */
    private Integer categoryId;
    /**
     * 名称
     */
    private String name;
    /**
     * 数量单位
     */
    private String unitName;
    /**
     * 销售价
     */
    private Float price;
    /**
     * 添加日期
     */
    private String createDate;
    /**
     * 产品描述
     */
    private String remark;
    /**
     * 库存数
     */
    private Integer num;
    /**
     * 采购在途数
     */
    private Integer poNum;
    /**
     * 销售待发数
     */
    private Integer soNum;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPoNum() {
        return poNum;
    }

    public void setPoNum(Integer poNum) {
        this.poNum = poNum;
    }

    public Integer getSoNum() {
        return soNum;
    }

    public void setSoNum(Integer soNum) {
        this.soNum = soNum;
    }
}
