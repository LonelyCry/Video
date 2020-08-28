package cn.ghe.video.service;

import cn.ghe.video.bean.FileEntity;
import cn.ghe.video.bean.IncorDO;

import java.util.List;
import java.util.Map;

public interface VideoService {
    List queryVideo(IncorDO name);

    void addVideo(FileEntity entity);

    String deleteVideo(int id);

    String updateVideo(Map map);

    String emptyVideo();

    String deleteBatchVideo(List list);

    List playVideo();

    int queryTotal();

    String queryByordernum(String order_num);

    String queryFileUrl(int id);
}
