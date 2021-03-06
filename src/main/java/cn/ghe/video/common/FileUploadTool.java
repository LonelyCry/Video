package cn.ghe.video.common;

import cn.ghe.video.bean.FileEntity;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

public class FileUploadTool {
    TransfMediaTool transfMediaTool = new TransfMediaTool();
    // 文件最大500M
    private static long upload_maxsize = 800 * 1024 * 1024;
    // 文件允许格式
    private static String[] allowFiles = { ".rar", ".doc", ".docx", ".zip",
            ".pdf", ".txt", ".swf", ".xlsx", ".gif", ".png", ".jpg", ".jpeg",
            ".bmp", ".xls", ".mp4", ".flv", ".ppt", ".avi", ".mpg", ".wmv",
            ".3gp", ".mov", ".asf", ".asx", ".vob", ".wmv9", ".rm", ".rmvb" };
    // 允许转码的视频格式（ffmpeg）
    private static String[] allowFLV = { ".avi", ".mpg", ".wmv", ".3gp",
            ".mov", ".asf", ".asx", ".vob" };
    // 允许的视频转码格式(mencoder)
    private static String[] allowAVI = { ".wmv9", ".rm", ".rmvb" };

    public FileEntity createFile(MultipartFile multipartFile, HttpServletRequest request, String order_num) {
        FileEntity entity = new FileEntity();
        boolean bflag = false;
        String fileName = multipartFile.getOriginalFilename().toString();
        // 判断文件不为空
        if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
            bflag = true;
            // 判断文件大小
            if (multipartFile.getSize() <= upload_maxsize) {
                bflag = true;
                // 文件类型判断
                if (this.checkFileType(fileName)) { bflag = true;
                } else {
                    bflag = false;
                    System.out.println("文件类型不允许");
                }
            } else {
                bflag = false;
                System.out.println("文件大小超范围");
            }
        } else {
            bflag = false;
            System.out.println("文件为空");
        }
        if (bflag) {
            //String logoPathDir = "/video/upload/";
            //String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
            // 上传到本地磁盘
            //String logoRealPathDir = "D:/upload";
            //linux文件
            String logoRealPathDir = "/video" + File.separator + "upload";
            File logoSaveFile = new File(logoRealPathDir);
            if (!logoSaveFile.exists()) {
                logoSaveFile.mkdirs();
            }
            //todo:文件名称需要考虑，当上传相同文件名称的文件时怎么处理 ok
            int n = fileName.lastIndexOf(".");
            int l = fileName.lastIndexOf("\\") + 1;
            String name = fileName.substring(l,n);
            System.out.println("文件名称：" + name);
            //使用序号加+源文件名进行处理
            // 新的文件名
            String newFileName = order_num+name;
            // 文件扩展名
            String fileEnd = this.getFileExt(fileName);
            // 绝对路径
            /*String oldNamedirs = logoRealPathDir + File.separator + name + fileEnd;
            String fileNamedirs = logoRealPathDir + File.separator + newFileName + fileEnd;*/
            String oldNamedirs = logoRealPathDir + "/" + name + fileEnd;
            String fileNamedirs = logoRealPathDir + "/" + newFileName + fileEnd;
            System.out.println("保存的绝对路径：" + fileNamedirs);
            File filedirs = new File(fileNamedirs);
            File oldfiledirs = new File(oldNamedirs);
            FileDeleteTool fileDeleteTool = new FileDeleteTool();
            // 转入文件
            try {
                multipartFile.transferTo(oldfiledirs);
                copyFile(oldNamedirs,fileNamedirs);
                VideoConvert v = new VideoConvert();


                FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filedirs);
                grabber.start();
                int codec = grabber.getVideoCodec();
                grabber.close();

                if(avcodec.AV_CODEC_ID_H264 == codec){
                    fileDeleteTool.delFile(oldNamedirs);
                }else{
                    fileDeleteTool.delFile(fileNamedirs);
                    v.frameRecord(oldNamedirs, fileNamedirs);
                    fileDeleteTool.delFile(oldNamedirs);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 相对路径
            entity.setType(fileEnd);
            /*String fileDir = logoPathDir + newFileName + fileEnd;
            StringBuilder builder = new StringBuilder(fileDir);
            String finalFileDir = builder.substring(1);*/
            // size存储为String
            String size = this.getSize(filedirs);
            // 源文件保存路径
            String aviPath = filedirs.getAbsolutePath();
            // 转码Avi
//            boolean flag = false;
           /* if (this.checkAVIType(fileEnd)) {
                // 设置转换为AVI格式后文件的保存路径
                String codcAviPath = logoRealPathDir + File.separator + newFileName + ".avi";
                // 获取配置的转换工具（mencoder.exe）的存放路径
                //String mencoderPath = "video" + File.separator + "file" + File.separator + "tools" + File.separator + "mencoder.exe";
                String mencoderPath = "D:/upload/tools/mencoder.exe";
                //String mencoderPath = request.getSession().getServletContext().getRealPath("/tools/mencoder.exe");
                aviPath = transfMediaTool.processAVI(mencoderPath, filedirs.getAbsolutePath(), codcAviPath);
                fileEnd = this.getFileExt(codcAviPath);
            }*/
            if (aviPath != null) {
                // 转码Flv
               /* if (this.checkMediaType(fileEnd)) {
                    try {
                        // 设置转换为flv格式后文件的保存路径
                        String codcFilePath = logoRealPathDir + File.separator + newFileName + ".flv";
                        // 获取配置的转换工具（ffmpeg.exe）的存放路径
                        //String ffmpegPath = "video" + File.separator + "file" + File.separator + "tools" + File.separator + "ffmpeg.exe";
                        //String ffmpegPath = request.getSession().getServletContext().getRealPath("/tools/ffmpeg.exe");
                        String ffmpegPath = "D:/upload/tools/ffmpeg.exe";
                        transfMediaTool.processFLV(ffmpegPath, aviPath, codcFilePath);
                        fileDir = logoPathDir + newFileName + ".flv";
                        builder = new StringBuilder(fileDir);
                        finalFileDir = builder.substring(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/
                String duration = ReadVideoTime(fileNamedirs);
                entity.setDuration(duration);
                entity.setSize(size);
                entity.setOldpath(oldNamedirs);
                entity.setPath(fileNamedirs);
                entity.setTitleOrig(name);
                entity.setTitleAlter(newFileName);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
                String createTime = sdf.format(date);  //将当前时间格式化为需要的类型
                entity.setUploadTime(timestamp);
                entity.setCreateTime(createTime);
                return entity;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public void changeFile(String fileNamedirs, String oldNamedirs) throws IOException, QtFastStart.UnsupportedFileException, QtFastStart.MalformedFileException {
        boolean success = QtFastStart.fastStart(fileNamedirs, oldNamedirs);
        File file = new File(oldNamedirs);
        File file1 = new File(fileNamedirs);
        FileDeleteTool fileDeleteTool = new FileDeleteTool();
        System.out.println(file.exists());
        if(file.exists() && file1.exists()){
            fileDeleteTool.delFile(fileNamedirs);
            file.renameTo(file1);
            System.out.println("renameToNew--------------------");
        }else if(file.exists() && !file1.exists()){
            file.renameTo(file1);
        }
    }


    public void qtFile(String fileNamedirs, String oldNamedirs) throws IOException {
        String ruti = "cmd /c start ";
        String qt = "D://luzhou//qt-faststart.exe ";
        String s = "";
        Runtime runtime=Runtime.getRuntime();
        s = ruti + qt + fileNamedirs + " " + oldNamedirs;
        System.out.println("1");
        Process process = runtime.exec(s);
        System.out.println("执行完毕");

        //关闭流释放资源
        if(process != null){
            process.getOutputStream().close();
        }


        InputStream in = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String tmp = null;
        while ((tmp = br.readLine()) != null) {

        }
        File file = new File(oldNamedirs);
        FileDeleteTool fileDeleteTool = new FileDeleteTool();
        System.out.println(file.exists());
        if(file.exists()){
            fileDeleteTool.delFile(fileNamedirs);
            file.renameTo(new File(fileNamedirs));
            System.out.println("renameToNew--------------------");
        }
    }


    private void copyFile(String fileNamedirs, String oldNamedirs) {
        File f1=new File(fileNamedirs);
        File f2=new File(oldNamedirs);
        //2.提供相应的流对象
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try{
            fis=new FileInputStream(f1);
            fos=new FileOutputStream(f2);
            //3.实现复制
            byte[]b=new byte[200];
            int len;
            while((len=fis.read(b))!=-1){
                fos.write(b, 0, len);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    private boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 视频类型判断(flv)
     *
     * @param fileEnd
     * @return
     */
    private boolean checkMediaType(String fileEnd) {
        Iterator<String> type = Arrays.asList(allowFLV).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileEnd.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 视频类型判断(AVI)
     *
     * @param fileEnd
     * @return
     */
    private boolean checkAVIType(String fileEnd) {
        Iterator<String> type = Arrays.asList(allowAVI).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileEnd.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     * @return
     */
    private String getName(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.contains(ext)) {
                String newFileName = fileName.substring(0, fileName.lastIndexOf(ext));
                return newFileName;
            }
        }
        return "";
    }

    /**
     * 文件大小，返回kb.mb
     *
     * @return
     */
    private String getSize(File file) {
        String size = "";
        long fileLength = file.length();
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1024) {
            size = df.format((double) fileLength) + "BT";
        } else if (fileLength < 1048576) {
            size = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824) {
            size = df.format((double) fileLength / 1048576) + "MB";
        } else {
            size = df.format((double) fileLength / 1073741824) + "GB";
        }
        return size;
    }

    public String ReadVideoTime(String source) {
        File f = new File(source);
        String length = "";
        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(source);
            grabber.start();
            int codec = grabber.getVideoCodec();
            long duration = grabber.getLengthInTime() / 1000;
            grabber.close();
//            long ls = md.getDuration()/1000;
            long ls = duration/1000;
            int hour = (int) (ls/3600);
            int minute = (int) (ls%3600)/60;
            int second = (int) (ls-hour*3600-minute*60);
            String h = (hour < 10)?"0"+hour:String.valueOf(hour);
            String m = (minute < 10)?"0"+minute:String.valueOf(minute);
            String s = (second < 10)?"0"+second:String.valueOf(second);
            length = h+":"+m+":"+s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }
}
