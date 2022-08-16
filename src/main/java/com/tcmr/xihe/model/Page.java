package com.tcmr.xihe.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *      分页
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/13 22:59
 **/
@ApiModel("分页数据模型")
public class Page<T> extends PageRequest {

    private static final long serialVersionUID = -1144885844490975865L;

    @ApiModelProperty("总行数")
    protected int total = -1;

    @ApiModelProperty("总页数")
    protected int totalPageNo = 0;

    @ApiModelProperty("数据")
    protected List<T> objects = null;


    @ApiModelProperty("是否是第一页")
    protected boolean firstPage;

    @ApiModelProperty("是否是最后一页")
    protected boolean lastPage;

    @ApiModelProperty("是否有下一页")
    protected boolean nextPage;

    public Page() {
    }

    public Page(PageRequest pageRequest) {
        this.pageNo = pageRequest.pageNo;
        this.pageRow = pageRequest.pageRow;
        this.countTotal = pageRequest.countTotal;
        this.firstPage = this.isFirstPage();
        this.lastPage = this.isLastPage();
    }



    public Page(PageRequest pageRequest, int total, List<T> objects) {
        this.pageNo = pageRequest.pageNo;
        this.pageRow = pageRequest.pageRow;
        this.countTotal = pageRequest.countTotal;
        this.total = total;
        this.objects = objects;
        this.totalPageNo = this.getTotalPageNo();
        this.lastPage = this.isLastPage();
        this.firstPage = this.isFirstPage();
        this.nextPage = this.hasNextPage();
    }

    public List<T> getObjects() {
        return this.objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Iterator<T> iterator() {
        return this.objects.iterator();
    }

    public int getTotalPageNo() {
        return (int)Math.ceil((double)this.total / (double)this.getPageRow());
    }

    public boolean hasNextPage() {
        return this.getPageNo() + 1 <= this.getTotalPageNo();
    }

    public boolean isLastPage() {
        return this.getPageNo() == this.getTotalPageNo();
    }

    public int getNextPageNo() {
        return this.hasNextPage() ? this.getPageNo() + 1 : this.getPageNo();
    }

    public boolean hasPrePage() {
        return this.getPageNo() > 1;
    }

    public boolean isFirstPage() {
        return !this.hasPrePage();
    }

    public int getPrePage() {
        return this.hasPrePage() ? this.getPageNo() - 1 : this.getPageNo();
    }

    public List<Integer> getSlider(int count) {
        int halfSize = count / 2;
        int totalPage = this.getTotalPageNo();
        int startPageNo = Math.max(this.getPageNo() - halfSize, 1);
        int endPageNo = Math.min(startPageNo + count - 1, totalPage);
        if (endPageNo - startPageNo < count) {
            startPageNo = Math.max(endPageNo - count, 1);
        }

        List<Integer> objects = new ArrayList<>();

        for(int i = startPageNo; i <= endPageNo; ++i) {
            objects.add(i);
        }

        return objects;
    }

    public List<Integer> getSlider() {
        int count = 10;
        return this.getSlider(count);
    }
}
