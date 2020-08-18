package cn.ghe.video.bean;

/**
 * 接收前端传递的给后台的参数
 */
public class IncorDO {
    //当前页码
    private int currentPage;
    //分页条数
    private int pageSize;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
