package cn.ghe.video.service;

import cn.ghe.video.bean.FileEntity;
import cn.ghe.video.bean.IncorDO;
import cn.ghe.video.bean.Rest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface VideoService {
    List queryVideo(IncorDO name) throws Exception;

    void addVideo(FileEntity entity) throws Exception;

    String deleteVideo(IncorDO incorDO) throws Exception;

    String emptyVideo() throws Exception;

    String deleteBatchVideo(IncorDO incorDO) throws Exception;

    List playVideo() throws Exception;

    int queryTotal() throws Exception;

    String queryByordernum(String order_num) throws Exception;

    String queryFileUrl(int id) throws Exception;

    String queryByid(Map map) throws Exception;

    Rest updateVideo(MultipartFile multipartFile, HttpServletRequest request) throws Exception;
}
