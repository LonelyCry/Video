package cn.ghe.video.dao;


import cn.ghe.video.bean.VideoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoDao {
    List queryVideo(String name);

    int deleteVideo(String id);

    void addVideo();

    int updateVideo(VideoDO videoDO);
}
