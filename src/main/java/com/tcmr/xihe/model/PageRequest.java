package com.tcmr.xihe.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 22:59
 **/
public class PageRequest implements Serializable {

    @ApiModelProperty("页码")
    protected int pageNo = 1;

    @ApiModelProperty("每页数据量")
    protected int pageRow = 20;
    protected boolean countTotal = true;

    public PageRequest() {
    }

    public PageRequest(int pageNo, int pageRow) {
        this.pageNo = pageNo;
        this.pageRow = pageRow;
    }

    public PageRequest(int pageNo, int pageRow, boolean countTotal) {
        this.pageNo = pageNo;
        this.pageRow = pageRow;
        this.countTotal = countTotal;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo > 0) {
            this.pageNo = pageNo;
        } else {
            this.pageNo = 1;
        }

    }

    public int getPageRow() {
        return this.pageRow;
    }

    public void setPageRow(int pageRow) {
        if (pageRow > 0) {
            this.pageRow = pageRow;
        } else {
            this.pageRow = 1;
        }

    }

    public boolean getCountTotal() {
        return this.countTotal;
    }

    public void setCountTotal(boolean countTotal) {
        this.countTotal = countTotal;
    }

    public int getOffset() {
        return (this.pageNo - 1) * this.pageRow;
    }
}
