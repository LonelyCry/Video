package cn.ghe.video.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 视频类
 */import java.sql.Date;

@Data
public class VideoDO {
    @ApiModelProperty(value = "ID")
    private String id;
    @ApiModelProperty(value = "视频名称", required = true)
    private String name;
    @ApiModelProperty(value = "序号", required = true)
    private String ordernum;
    @ApiModelProperty(value = "时长", required = true)
    private String duration;
    @ApiModelProperty(value = "更新时间", required = true)
    private Date uploadtime;
    @ApiModelProperty(value = "上传时间", required = true)
    private Date createtime;
    @ApiModelProperty(value = "创建人", required = true)
    private String createname;
    @ApiModelProperty(value = "文件存放路径", required = true)
    private String fileurl;
    @ApiModelProperty(value = "源文件名", required = true)
    private String oldname;
    @ApiModelProperty(value = "新文件名", required = true)
    private String newname;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getOldname() {
        return oldname;
    }

    public void setOldname(String oldname) {
        this.oldname = oldname;
    }

    public String getNewname() {
        return newname;
    }

    public void setNewname(String newname) {
        this.newname = newname;
    }
}
