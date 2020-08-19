package cn.ghe.video.dao;


import cn.ghe.video.bean.IncorDO;
import cn.ghe.video.bean.VideoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VideoDao {
    List queryVideo(Map map);

    int deleteVideo(String id);

    void addVideo();

    int updateVideo(VideoDO videoDO);

    int deleteBatchVideo(List list);

    void deleteVideo();

    List playVideo(List list);

    int queryTotal();
}
