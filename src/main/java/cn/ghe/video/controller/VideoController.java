package cn.ghe.video.controller;

import cn.ghe.video.bean.FileEntity;
import cn.ghe.video.bean.IncorDO;
import cn.ghe.video.bean.Rest;
import cn.ghe.video.bean.VideoDO;
import cn.ghe.video.common.FileDeleteTool;
import cn.ghe.video.common.FileUploadTool;
import cn.ghe.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


@Api(value="视频管理接口",description = "视频管理页面管理接口，提供视频的增、删、改、查",tags = "视频管理")
@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;  //注入Service

    @ApiOperation(value = "上传视频", notes = "根据上传的视频进行添加")
    @ApiImplicitParam(name = "VideoDO", value = "视频详情实体类", required = true, dataType = "VideoDO")
    @RequestMapping(value = "/addVideo", method = RequestMethod.POST)
    //RequestBody:接受前端传递的json数据
    public String addVideo(@RequestBody VideoDO videoDO) {
        //videoService.addVideo(entity);
        return "添加成功~~~";
    }

    @ApiOperation(value = "删除单条视频", notes = "根据单条视频主键id进行删除")
    @ApiImplicitParam(name = "incorDO", value = "视频详情实体类主键id", required = true, dataType = "IncorDO")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Rest deleteVideo(@RequestBody IncorDO incorDO) {
        Rest rest = new Rest();
        Map m = (Map) incorDO.getValues().get(0);
        String filePathAndName = queryFileUrl(Integer.parseInt(m.get("id").toString()));
        String flag = videoService.deleteVideo(Integer.parseInt(m.get("id").toString()));
        FileDeleteTool fileDeleteTool = new FileDeleteTool();
        fileDeleteTool.delFile(filePathAndName);
        if ("success".equals(flag)) {
            rest.setMsgType("success");
            rest.setMsg("删除成功！");
            rest.setSuccess(true);
        } else {
            rest.setMsgType("error");
            rest.setMsg("删除失败！");
            rest.setSuccess(false);
        }
        return rest;
    }

    @ApiOperation(value = "批量删除视频", notes = "根据批量视频主键id进行删除")
    @ApiImplicitParam(name = "incorDO", value = "视频详情实体类主键id数组", required = true, dataType = "IncorDO")
    @RequestMapping(value = "/deleteBatchVideo", method = RequestMethod.POST)
    @ResponseBody
    public Rest deleteBatchVideo(@RequestBody IncorDO incorDO) {
        Rest rest = new Rest();
        List list = incorDO.getValues();
        String flag = videoService.deleteBatchVideo(list);
        FileDeleteTool fileDeleteTool = new FileDeleteTool();
        for (int i = 0;i < list.size();i++){
            Map m = (Map) list.get(i);
            String url = m.get("fileurl").toString();
            fileDeleteTool.delFile(url);
        }
        if ("success".equals(flag)) {
            rest.setMsgType("success");
            rest.setMsg("删除成功！");
            rest.setSuccess(true);
        } else {
            rest.setMsgType("error");
            rest.setMsg("删除失败！");
            rest.setSuccess(false);
        }
        return rest;
    }

    @ApiOperation(value = "查询视频", notes = "根据参数查询视频并返回给前端")
    @ApiImplicitParam(name = "incorDO", value = "视频分页信息", required = true, dataType = "incorDO")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Rest queryVideo(@RequestBody IncorDO incorDO) {
        Rest rest = new Rest();
        List list = new ArrayList();
        list = videoService.queryVideo(incorDO);
        rest.setList(list);
        rest.setPageSize(incorDO.getPageSize());
        rest.setPage(incorDO.getPage());
        rest.setTotal(queryTotal());
        rest.setMsg("查询成功");
        rest.setSuccess(true);
        return rest;
    }

    @ApiOperation(value = "更新视频", notes = "更新视频")
    @ApiImplicitParam(name = "incorDO", value = "测试", required = true, dataType = "IncorDO")
    @RequestMapping(value = "/updateVideo", method = RequestMethod.POST)
    @ResponseBody
    public String updateVideo(@RequestBody IncorDO incorDO) {
        //String flag = videoService.updateVideo(videoDO);
        Rest rest = new Rest();
        List listVideo = new ArrayList();
        List values = incorDO.getValues();
        return "flag";
    }

    @ApiOperation(value = "清空数据", notes = "清空数据")
    @RequestMapping(value = "/empty", method = RequestMethod.POST)
    @ResponseBody
    public Rest emptyVideo() {
        Rest rest = new Rest();
        try {
            String flag = videoService.emptyVideo();
            rest.setMsgType("success");
            rest.setMsg("清空成功！");
            rest.setSuccess(true);
        }catch (Exception e){
            rest.setMsgType("error");
            rest.setMsg("清空失败！");
            rest.setSuccess(false);
            e.printStackTrace();
        }
        return rest;
    }

    @ApiOperation(value = "视频预览", notes = "视频预览")
    @ApiImplicitParam(name = "incorDO", value = "视频详情实体类主键id数组", required = true, dataType = "IncorDO")
    @RequestMapping(value = "/play", method = RequestMethod.POST)
    @ResponseBody
    public Rest playVideo(@RequestBody IncorDO incorDO) {
        Rest rest = new Rest();
        List listVideo = new ArrayList();
        List values = incorDO.getValues();
        listVideo = videoService.playVideo(values);
        if (values.size() == listVideo.size()) {
            rest.setSuccess(true);
            rest.setMsgType("success");
            rest.setMsg("视频预览成功！");
            rest.setVideoList(listVideo);
        } else {
            rest.setSuccess(false);
            rest.setMsg("视频预览失败！");
            rest.setMsgType("error");
        }
        return rest;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public Rest upload(@RequestParam(value = "filevideo", required = false) MultipartFile multipartFile,
                       HttpServletRequest request) {
        StandardMultipartHttpServletRequest httpServletRequest = (StandardMultipartHttpServletRequest) request;
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        System.out.println("-------------------ParameterNames------------------");
        String name = "";
        String order_num = "";
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            if ("name".equals(key)) {
                name = value;
            }
            if ("ordernum".equals(key)) {
                order_num = value;
            }
                 /*    ;E9ugXxefO;Z    HGsr5NeL=E8l  ;Gtt.gk2r3lw */
        }
        /*System.out.println("-------------------AttributeNames------------------");
        Enumeration<String> attributeNames = httpServletRequest.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String key = attributeNames.nextElement();
            System.out.println("key = " + key);
        }
        System.out.println("-------------------HeaderNames------------------");
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            System.out.println(String.format("key: %s, value:%s",key,value));
        }*/
        /*System.out.println("-------------------FileNames------------------");
        Iterator<String> iterator = httpServletRequest.getFileNames();
        System.out.println(iterator.hasNext() + "     ss s ");
        while (iterator.hasNext()) {
            MultipartFile file = httpServletRequest.getFile(iterator.next());
            String fileNames = file.getOriginalFilename();
            int split = fileNames.lastIndexOf(".");
            System.out.println("fileNames = " + fileNames);
        }*/

        String message = "";
        FileEntity entity = new FileEntity();
        Rest map = new Rest();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            String flag = "";
            if(!"".equals(order_num)){
                flag = videoService.queryByordernum(order_num);
            }
            if("success".equals(flag)){
                entity = fileUploadTool.createFile(multipartFile, request, order_num);
                message = "上传失败";
            }else {
                message = "序号已经存在，请修改！";
            }

            if (entity != null && "success".equals(flag)) {
                entity.setName(name);
                entity.setOrdernum(order_num);
                videoService.addVideo(entity);
                message = "上传成功";
                map.setSuccess(true);
                map.setMsgType("success");
            } else {
                map.setSuccess(false);
                map.setMsgType("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.setMsg(message);
        return map;
    }

    /**
     * 获取视频流
     * @param
     * @param
     * @return
     */
   /* @RequestMapping("/getVideo/{videoId}")
    public void getVideo(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer videoId)
    {
        //视频资源存储信息
        //FileEntity videoSource = videoService.selectById(videoId);
        response.reset();
        //获取从那个字节开始读取文件
        String rangeString = request.getHeader("Range");

        try {
            //获取响应的输出流
            OutputStream outputStream = response.getOutputStream();
            File file = new File(videoSource.getFileAddress());
            if(file.exists()){
                RandomAccessFile targetFile = new RandomAccessFile(file, "r");
                long fileLength = targetFile.length();
                //播放
                if(rangeString != null){

                    long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
                    //设置内容类型
                    response.setHeader("Content-Type", "video/mp4");
                    //设置此次相应返回的数据长度
                    response.setHeader("Content-Length", String.valueOf(fileLength - range));
                    //设置此次相应返回的数据范围
                    response.setHeader("Content-Range", "bytes "+range+"-"+(fileLength-1)+"/"+fileLength);
                    //返回码需要为206，而不是200
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                    //设定文件读取开始位置（以字节为单位）
                    targetFile.seek(range);
                }else {//下载

                    //设置响应头，把文件名字设置好
                    response.setHeader("Content-Disposition", "attachment; filename="+videoSource.getFileName() );
                    //设置文件长度
                    response.setHeader("Content-Length", String.valueOf(fileLength));
                    //解决编码问题
                    response.setHeader("Content-Type","application/octet-stream");
                }
                byte[] cache = new byte[1024 * 300];
                int flag;
                while ((flag = targetFile.read(cache))!=-1){
                    outputStream.write(cache, 0, flag);
                }
            }else {
                String message = "file:"+videoSource.getFileName()+" not exists";
                //解决编码问题
                response.setHeader("Content-Type","application/json");
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            }

            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public String queryFileUrl(int id){
        return videoService.queryFileUrl(id);
    }

    /**
     * 查询总数
     */
    public int queryTotal() {
        int total = videoService.queryTotal();
        return total;
    }



    @RequestMapping(value ="/getFileSrc" ,method = RequestMethod.POST)
    @ResponseBody
    public void getFileSrc(HttpServletRequest request , HttpServletResponse response, @RequestParam(value="path") String path) throws IOException {
        File file = new File(path);
        FileInputStream input = new FileInputStream(file);
        int i = input.available();
        byte[] bytes = new byte[i];
        input.read(bytes);
        response.setContentType("application/video");
        OutputStream output = response.getOutputStream();
        output.write(bytes);
        output.flush();
        output.close();
        input.close();
    }
}
