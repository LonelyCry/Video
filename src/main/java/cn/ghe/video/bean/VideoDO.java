package cn.ghe.video.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String order_num;
    @ApiModelProperty(value = "时长", required = true)
    private String duration;
    @ApiModelProperty(value = "更新时间", required = true)
    private Date upload_time;
    @ApiModelProperty(value = "上传时间", required = true)
    private Date create_time;
    @ApiModelProperty(value = "创建人", required = true)
    private String create_name;
    @ApiModelProperty(value = "文件存放路径", required = true)
    private String file_url;

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

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }
}
