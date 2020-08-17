package cn.ghe.video.controller;

import cn.ghe.video.bean.VideoDO;
import cn.ghe.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value="视频管理接口",description = "视频管理页面管理接口，提供视频的增、删、改、查",tags = "视频管理")
@Controller
@RequestMapping("/api")
public class VideoController {

    @Autowired
    private VideoService videoService;  //注入Service

    @ApiOperation(value = "上传视频" , notes = "根据上传的视频进行添加")
    @ApiImplicitParam(name = "VideoDO",value = "视频详情实体类",required = true,dataType = "VideoDO")
    @RequestMapping(value = "/addVideo",method = RequestMethod.POST)
    //RequestBody:接受前端传递的json数据
    public String addVideo(@RequestBody VideoDO videoDO) {
        videoService.addVideo();
        return "添加成功~~~";
    }

    @ApiOperation(value = "删除视频" , notes = "根据视频主键id进行删除")
    @ApiImplicitParam(name = "id",value = "视频详情实体类主键id",required = true,dataType = "String")
    @RequestMapping(value = "/deleteVideo",method = RequestMethod.POST)
    //RequestBody:接受前端传递的json数据
    public String deleteVideo(String id) {
        String flag = videoService.deleteVideo(id);
        return "添加成功~~~";
    }

    @ApiOperation(value = "查询视频" , notes = "根据视频名称查询视频并返回给前端")
    @ApiImplicitParam(name = "name",value = "视频名称",required = true,dataType = "String")
    @RequestMapping(value = "/queryVideo",method = RequestMethod.POST)
    @ResponseBody
    public List queryVideo(String name) {
        List map = new ArrayList();
        map = videoService.queryVideo(name);
        return map;
    }

    @ApiOperation(value = "更新视频" , notes = "更新视频")
    @ApiImplicitParam(name = "VideoDO",value = "测试",required = true,dataType = "VideoDO")
    @RequestMapping(value = "/updateVideo",method = RequestMethod.POST)
    @ResponseBody
    public String updateVideo(@RequestBody VideoDO videoDO) {
        String flag = videoService.updateVideo(videoDO);
        return flag;
    }

}
