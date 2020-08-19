package cn.ghe.video.service;

import cn.ghe.video.bean.IncorDO;
import cn.ghe.video.bean.VideoDO;

import java.util.List;

public interface VideoService {
    List queryVideo(IncorDO name);

    void addVideo();

    String deleteVideo(String id);

    String updateVideo(VideoDO videoDO);

    void emptyVideo();

    String deleteBatchVideo(List list);

    List playVideo(List list);

    int queryTotal();
}
