package cn.ghe.video.bean;

import java.util.List;

/**
 * 返回给前端的结果
 */
public class Rest {

    //登录成功返回值
    private String status;

    //返回信息：登录成功、上传成功
    private String msg;

    //状态：true/false
    private Boolean success;

    //信息列表
    private List list;

    //所有数据
    private int total;

    //分页条数
    private int pageSize;

    //当前页面
    private int page;

    //视频预览时，返回的新list
    private List videoList;

    //判断用户权限：admin:登录成功，guest:登录失败
    private String currentAuthority;

    public String getCurrentAuthority() {
        return currentAuthority;
    }

    public void setCurrentAuthority(String currentAuthority) {
        this.currentAuthority = currentAuthority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List getVideoList() {
        return videoList;
    }

    public void setVideoList(List videoList) {
        this.videoList = videoList;
    }
}
