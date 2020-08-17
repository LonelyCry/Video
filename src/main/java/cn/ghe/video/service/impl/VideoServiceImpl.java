package cn.ghe.video.service.impl;

import cn.ghe.video.bean.VideoDO;
import cn.ghe.video.dao.VideoDao;
import cn.ghe.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;  //注入UserDao


    @Override
    public List queryVideo(String name) {
        List m = videoDao.queryVideo(name);
        return m;
    }

    @Override
    public void addVideo() {
        videoDao.addVideo();
    }

    @Override
    public String deleteVideo(String id) {
        String flag;
        int i = videoDao.deleteVideo(id);
        if(i == 1){
            flag = "success";
        }else {
            flag = "false";
        }
        return flag;
    }

    @Override
    public String updateVideo(VideoDO videoDO) {
        String flag;
        int i = videoDao.updateVideo(videoDO);
        if(i == 1){
            flag = "success";
        }else {
            flag = "false";
        }
        return flag;
    }
}
