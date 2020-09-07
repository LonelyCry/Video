package cn.ghe.video.common;


public class ee {

    /**
     * 截取视频的一针作为封面图
     *
     * @param file          视频文件
     * @param thumbnailPath 截取图片保存路径
     * @return
     */
   /* public void getVideoInfoAndGenerateThumbnail(File file, String thumbnailPath) {
        MultimediaObject multimediaObject = new MultimediaObject(file);
        try {
            MultimediaInfo info = multimediaObject.getInfo();
            VideoInfo videoInfo = info.getVideo();
            System.out.println("获取视频时长：{}"+info.getDuration() / 1000);
            VideoSize size = videoInfo.getSize();
            int width = size.getWidth();
            int height = size.getHeight();
            System.out.println("视频宽：{} 视频高{}"+width+height);
            System.out.println("比特率：{}"+videoInfo.getBitRate() / 1000);
        } catch (EncoderException e) {
            e.printStackTrace();
        }

    }*/


    /**
     * @param
     * @param targetPath 转码后的路径
     */
    /*public void convertVideoToMP4(File source, String targetPath) {
        MultimediaObject multimediaObject = new MultimediaObject(source);
        try {
            MultimediaInfo info = multimediaObject.getInfo();
            VideoInfo videoInfo = info.getVideo();
            VideoSize size = videoInfo.getSize();
            System.out.println("原视频宽：" + size.getWidth());
            System.out.println("原视频高：" + size.getHeight());
            System.out.println("原视频比特率：" + videoInfo.getBitRate() / 1000);
            System.out.println("原视频编码：" + videoInfo.getDecoder());

            VideoAttributes video = new VideoAttributes();
            //设置视频编码
            video.setCodec("libx264");

            File target = new File(targetPath);
            AudioAttributes audio = new AudioAttributes();
            //设置编码器名称
            audio.setCodec("libmp3lame");
            EncodingAttributes attrs = new EncodingAttributes();
            //设置转换后的格式
            attrs.setFormat("mp4");
            //attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            Encoder encoder = new Encoder();
            encoder.encode(multimediaObject, target, attrs);
            //花费毫秒数

            MultimediaObject multimediaObjectOfter = new MultimediaObject(Paths.get(targetPath).toFile());
            MultimediaInfo info1 = multimediaObjectOfter.getInfo();
            VideoInfo video1 = info1.getVideo();
            VideoSize size1 = video1.getSize();

            System.out.println("转换后视频宽：" + size1.getWidth());
            System.out.println("转换后视频高：" + size1.getHeight());
            System.out.println("转换后视频比特率：" + video1.getBitRate() / 1000);
            System.out.println("转换后视频编码：" + video1.getDecoder());

        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }*/
    /*public void convertMultimediaFormat(String sourcePath, String targetPath, String audioEncoder, String videoEncoder,
                                        String targetFormat) {
        try {
			*//*File source = new File("C:\\Users\\Administrator\\Pictures\\新建文件夹\\3-6 webpack打包（中）.avi");
			File target = new File("C:\\Users\\Administrator\\Pictures\\新建文件夹\\4-3.mp4");*//*
            File source = new File(sourcePath);
            File target = new File(targetPath);
            *//*设置音频属性*//*
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec(audioEncoder); // libmp3lame  或  flac
            audio.setBitRate(new Integer(400000)); // 数字越大声音越接近原声，转换时间越久（亲测这个数值比较好）
            audio.setChannels(new Integer(1));

            // audio.setSamplingRate(new Integer(22050));
            *//*设置视频属性*//*
            VideoAttributes video = new VideoAttributes();
            video.setCodec(videoEncoder);  // msmpeg4v2
            video.setBitRate(new Integer(1600000)); // 数字越大画面越清晰，转换时间越久（亲测这个数值比较好）
            video.setFrameRate(new Integer(15));
            video.setSize(new VideoSize(1920, 1080));


            *//*设置编码属性*//*
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat(targetFormat); //mp4  需要转换成的格式
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            *//*执行转码*//*
            Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfo(source);
            System.out.println(m.getVideo().getDecoder());
            System.out.println(m.getVideo().getSize().getWidth());
            System.out.println(m.getVideo().getSize().getHeight());
            System.out.println(m.getVideo().getFrameRate());
            System.out.println(m.getVideo().getBitRate());
            System.out.println(m.getAudio().getChannels());
            System.out.println(m.getAudio().getBitRate());
            System.out.println(m.getAudio().getSamplingRate());
            System.out.println(m.getDuration());
            String[] s = encoder.getVideoEncoders();
            String[] ss = encoder.getSupportedDecodingFormats();
            *//*for (int i = 0;i < s.length;i++){
                System.out.println(s[i]  + " :" + ss[i]);
            }*//*
            System.out.println();
            //encoder.encode(source, target, attrs);

            System.out.println("转码成功！！！！！！！");

            File file = new File(targetPath);
            Encoder encoder1 = new Encoder();
            MultimediaInfo info = encoder1.getInfo(file);
            VideoInfo video1 = info.getVideo();
            String decoder1 = video1.getDecoder();
            System.out.println(decoder1);
        } catch (IllegalArgumentException e) {
            System.out.println("转码失败！（IllegalArgumentException）");
            e.printStackTrace();
        } catch (InputFormatException e) {
            System.out.println("转码失败！（InputFormatException）");
            e.printStackTrace();
        } catch (EncoderException e) {
            System.out.println("转码失败！（EncoderException）");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ee m = new ee();
        //
        String sourcePath = "C:\\Users\\17426\\Downloads\\123遇见·泸州老窖_Final版.mp4";
        //String sourcePath = "C:\\Users\\17426\\Downloads\\5b527330214a6.mp4";
        String targetPath = "C:\\Users\\17426\\Downloads\\Final版321.mp4";
        m.convertMultimediaFormat(sourcePath, targetPath, "libfaac", "libx264", "flv");
    }*/
}