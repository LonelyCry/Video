package cn.ghe.video.bean;

import java.util.List;

/**
 * 接收前端传递的给后台的参数
 */
public class IncorDO {
    //当前页码
    private int page;
    //分页条数
    private int pageSize;

    private List values;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getValues() {
        return values;
    }

    public void setValues(List values) {
        this.values = values;
    }
}
