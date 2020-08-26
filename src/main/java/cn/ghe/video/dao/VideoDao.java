package cn.ghe.video.dao;


import cn.ghe.video.bean.FileEntity;
import cn.ghe.video.bean.VideoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface VideoDao {
    List queryVideo(Map map);

    int deleteVideo(int id);

    void addVideo(FileEntity entity);

    int updateVideo(VideoDO videoDO);

    int deleteBatchVideo(List list);

    int emptyVideo();

    List playVideo(List list);

    int queryTotal();

    int queryByordernum(@Param(value="ordernum") String ordernum);

    String queryFileUrl(@Param(value="id") int id);
}
