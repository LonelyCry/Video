package cn.ghe.video.service.impl;

import cn.ghe.video.bean.FileEntity;
import cn.ghe.video.bean.IncorDO;
import cn.ghe.video.bean.VideoDO;
import cn.ghe.video.dao.VideoDao;
import cn.ghe.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;  //注入UserDao


    @Override
    public List queryVideo(IncorDO incorDO) throws RuntimeException{
        Map map = new HashMap<>();
        int pageSize = incorDO.getPageSize();
        int page = incorDO.getPage();
        int start_num = (page - 1) * pageSize;
        map.put("start_num",start_num);
        map.put("pageSize",pageSize);
        List m = videoDao.queryVideo(map);
        return m;
    }

    @Override
    public void addVideo(FileEntity entity) throws RuntimeException{
        videoDao.addVideo(entity);
    }

    @Override
    public String deleteVideo(int id) throws RuntimeException{
        String flag;
        //删除当前id在数据库中的记录
        int i = videoDao.deleteVideo(id);
        //todo:还需根据当前id查询视频保存路径，并进行删除
        if(i == 1){
            flag = "success";
        }else {
            flag = "false";
        }
        return flag;
    }

    @Override
    public String updateVideo(VideoDO videoDO) throws RuntimeException{
        String flag;
        int i = videoDao.updateVideo(videoDO);
        //todo:视频时长，更新时间
        //todo:时候重新上传了视频，是：需要先找到以前的视频进行删除，在进行视频更新，否：进行其余字段更新
        if(i == 1){
            flag = "success";
        }else {
            flag = "false";
        }
        return flag;
    }

    @Override
    public String emptyVideo() throws RuntimeException{
        videoDao.emptyVideo();
        return null;
    }

    @Override
    public String deleteBatchVideo(List list) throws RuntimeException{
        String flag;
        //删除当前id在数据库中的记录
        int i = videoDao.deleteBatchVideo(list);
        //todo:还需根据当前id查询视频保存路径，并进行删除
        if(i == list.size()){
            flag = "success";
        }else {
            flag = "false";
        }
        return flag;
    }

    @Override
    public List playVideo(List list) throws RuntimeException{
        List listVideo = videoDao.playVideo(list);
        return listVideo;
    }

    @Override
    public int queryTotal()throws RuntimeException {
        return videoDao.queryTotal();
    }

    @Override
    public String queryByordernum(String ordernum) throws RuntimeException{
        String flag;
        int num = videoDao.queryByordernum(ordernum);
        if(num > 0){
            flag = "false";
        }else {
            flag = "success";
        }
        return flag;
    }

    @Override
    public String queryFileUrl(int id) throws RuntimeException{
        return videoDao.queryFileUrl(id);
    }
}
