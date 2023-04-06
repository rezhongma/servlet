package com.powernode.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 此实体类对象中封装的是分页相关数据信息: 分页数据、总记录数、页码等
 */
public class PageInfo<T> {

    /**
     * 分页数据
     */
    private List<T> data = new ArrayList<>();

    /**
     * 满足sql条件的总记录数
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;

    public PageInfo() {
    }

    public PageInfo(List<T> data, long total, int pages) {
        this.data = data;
        this.total = total;
        this.pages = pages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "data=" + data +
                ", total=" + total +
                ", pages=" + pages +
                '}';
    }
}
