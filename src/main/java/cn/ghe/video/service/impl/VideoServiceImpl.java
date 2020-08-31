package cn.ghe.video.service.impl;

import cn.ghe.video.bean.FileEntity;
import cn.ghe.video.bean.IncorDO;
import cn.ghe.video.bean.Rest;
import cn.ghe.video.common.FileDeleteTool;
import cn.ghe.video.common.FileUploadTool;
import cn.ghe.video.dao.VideoDao;
import cn.ghe.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;  //注入UserDao


    @Override
    public List queryVideo(IncorDO incorDO) throws Exception{
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
    public void addVideo(FileEntity entity) throws Exception{
        videoDao.addVideo(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//回滚所需要的
    public String deleteVideo(IncorDO incorDO) throws Exception{
        String flag = "false";
        try {
            Map m = (Map) incorDO.getValues().get(0);
            String filePathAndName = queryFileUrl(Integer.parseInt(m.get("id").toString()));
            //todo:还需根据当前id查询视频保存路径，并进行删除  ok
            FileDeleteTool fileDeleteTool = new FileDeleteTool();
            fileDeleteTool.delFile(filePathAndName);
            //删除当前id在数据库中的记录
            int i = videoDao.deleteVideo(Integer.parseInt(m.get("id").toString()));
            if(i == 1){
                flag = "success";
            }else {
                flag = "false";
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("删除有误！");//回滚所需要的，返回错误
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//回滚所需要的
    public Rest updateVideo(MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        String flag = "false";
        Rest rest = new Rest();
        String message = "";
        try {
            StandardMultipartHttpServletRequest httpServletRequest = (StandardMultipartHttpServletRequest) request;
            Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
            System.out.println("-------------------ParameterNames------------------");
            Map map = new HashMap();
            while (parameterNames.hasMoreElements()) {
                String key = parameterNames.nextElement();
                String value = httpServletRequest.getParameter(key);
                map.put(key,value);
            }

            String ordernum = map.get("ordernum").toString();
            if(!"".equals(ordernum)){
                flag = queryByid(map);
            }
            int i = 0;
            if ("success".equals(flag)){
                if (!"1".equals(map.get("changefile"))){
                    i = videoDao.updateVideo(map);
                }else {
                    FileEntity entity = new FileEntity();
                    FileUploadTool fileUploadTool = new FileUploadTool();
                    FileDeleteTool fileDeleteTool = new FileDeleteTool();
                    fileDeleteTool.delFile(map.get("fileurl").toString());
                    entity = fileUploadTool.createFile(multipartFile, request, ordernum);
                    fileUploadTool.qtFile(entity.getPath(),entity.getOldpath());
                    if (entity != null && "success".equals(flag)) {
                        map.put("duration",entity.getDuration());
                        map.put("uploadtime",entity.getUploadTime());
                        map.put("fileurl",entity.getPath());
                        map.put("oldname",entity.getTitleOrig());
                        map.put("newname",entity.getTitleAlter());
                        i = videoDao.updateVideo(map);
                        message = "更新成功";
                        rest.setSuccess(true);
                        rest.setMsgType("success");
                    } else {
                        message = "更新失败";
                        rest.setSuccess(false);
                        rest.setMsgType("error");
                    }
                }
            }else {
                message = "序号已经存在，请修改！";
                rest.setSuccess(false);
                rest.setMsgType("error");
            }
            //todo:视频时长，更新时间
            //todo:时候重新上传了视频，是：需要先找到以前的视频进行删除，在进行视频更新，否：进行其余字段更新
        }catch (Exception e){
            message = "更新失败";
            rest.setSuccess(false);
            rest.setMsgType("error");
            e.printStackTrace();
            throw new Exception("更新视频有误！");//回滚所需要的，返回错误
        }
        return rest;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//回滚所需要的
    public String emptyVideo() throws Exception{
        videoDao.emptyVideo();
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//回滚所需要的
    public String deleteBatchVideo(IncorDO incorDO) throws Exception{
        String flag = "false";
        try {
            List<String> list_id = new ArrayList<String>();
            List list = incorDO.getValues();
            FileDeleteTool fileDeleteTool = new FileDeleteTool();
            for (int i = 0;i < list.size();i++){
                Map m = (Map) list.get(i);
                String url = m.get("fileurl").toString();
                fileDeleteTool.delFile(url);
                list_id.add(m.get("id").toString());
            }
            //删除当前id在数据库中的记录
            int i = videoDao.deleteBatchVideo(list_id);
            //todo:还需根据当前id查询视频保存路径，并进行删除  ok
            if(i == list.size()){
                flag = "success";
            }else {
                flag = "false";
            }
        }catch (Exception  e) {
            e.printStackTrace();
            throw new Exception("批量删除有误！");//回滚所需要的，返回错误
        }
        return flag;
    }

    @Override
    public List playVideo() throws Exception{
        List listVideo = videoDao.playVideo();
        return listVideo;
    }

    @Override
    public int queryTotal()throws Exception {
        return videoDao.queryTotal();
    }

    @Override
    public String queryByordernum(String ordernum) throws Exception{
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
    public String queryFileUrl(int id) throws Exception{
        return videoDao.queryFileUrl(id);
    }

    @Override
    public String queryByid(Map map) throws Exception {
        String flag;
        int num = videoDao.queryByid(map);
        if(num == 1){
            flag = "success";
        }else {
            flag = queryByordernum(map.get("ordernum").toString());
        }
        return flag;
    }
}
