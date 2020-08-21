package cn.ghe.video.bean;

import java.sql.Timestamp;

/**
 * 存放数据库入参数据
 */
public class FileEntity {
    //序号
    private String ordernum;
    //视频名称
    private String name;
    //视频时长
    private String duration;
    private String type;
    private String size;
    private String path;
    private String titleOrig;
    private String titleAlter;
    private Timestamp uploadTime;

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getTitleOrig() {
        return titleOrig;
    }
    public void setTitleOrig(String titleOrig) {
        this.titleOrig = titleOrig;
    }
    public String getTitleAlter() {
        return titleAlter;
    }
    public void setTitleAlter(String titleAlter) {
        this.titleAlter = titleAlter;
    }
    public Timestamp getUploadTime() {
        return uploadTime;
    }
    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }
}
