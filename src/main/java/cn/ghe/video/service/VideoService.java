package cn.ghe.video.service;

import cn.ghe.video.bean.VideoDO;

import java.util.List;

public interface VideoService {
    List queryVideo(String name);

    void addVideo();

    String deleteVideo(String id);

    String updateVideo(VideoDO videoDO);
}
