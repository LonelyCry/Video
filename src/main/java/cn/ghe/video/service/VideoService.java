package cn.ghe.video.service;

import cn.ghe.video.bean.FileEntity;
import cn.ghe.video.bean.IncorDO;
import cn.ghe.video.bean.VideoDO;

import java.util.List;

public interface VideoService {
    List queryVideo(IncorDO name);

    void addVideo(FileEntity entity);

    String deleteVideo(int id);

    String updateVideo(VideoDO videoDO);

    String emptyVideo();

    String deleteBatchVideo(List list);

    List playVideo();

    int queryTotal();

    String queryByordernum(String order_num);

    String queryFileUrl(int id);
}
