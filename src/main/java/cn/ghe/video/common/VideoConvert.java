package cn.ghe.video.common;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.io.File;

public class VideoConvert {
    public static void main(String[] args) throws Exception {
        VideoConvert m = new VideoConvert();
        String sourcePath = "D:\\FFOutput\\123遇见·泸州老窖_Final版.mp4";
        String targetPath = "D:\\FFOutput\\123遇见·泸州老窖_Final版1.mp4";
        m.frameRecord(sourcePath, targetPath);
    }
    private boolean isStart = true;

    public void frameRecord(String inputFile, String outputFile)
            throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        // Get the video source
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        grabber.start();
        int videoRate = grabber.getVideoBitrate();
        int height = grabber.getImageHeight();
        int width = grabber.getImageWidth();
        int channels = grabber.getAudioChannels();
        int audioRate = grabber.getAudioBitrate();
        double frameRate = grabber.getVideoFrameRate();

        //grabber.setOption("rtsp_transport","tcp");
        //speed
        grabber.setFrameRate(frameRate);
        // code rate

        //grabber.setVideoBitrate(videoRate);

        // Streaming media output address, resolution (length, height), whether to record audio (0: no recording / 1: recording)
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, width, height, channels);
        //recorder:输出
        recorder.setVideoOption("-movflags", "+faststart");
        //recorder.setVideoMetadata("rotate","90");
        recorder.setFrameRate(frameRate);
        recorder.setVideoBitrate(videoRate);
        recorder.setAudioBitrate(audioRate);
        //Encoding format
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        //avcodec.AV_CODEC_ID_H264  //AV_CODEC_ID_MPEG4
        recordByFrame(grabber, recorder);
    }

    private void recordByFrame(FFmpegFrameGrabber grabber, FFmpegFrameRecorder recorder)
            throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        try {// it is recommended to use this method in a thread
            recorder.start();
            //CanvasFrame canvas = new CanvasFrame("camera");//create a new window
            //     canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //canvas.setAlwaysOnTop(true);

            long t1 = System.currentTimeMillis();
            Frame frame = null;
            while (isStart && (frame = grabber.grabFrame()) != null) {
                long t2 = System.currentTimeMillis();
                if (t2 - t1 > 2 * 60 * 60 * 1000) {
                    break;
                } else {
                    recorder.record(frame);
                    //TODO your work
                }
                //canvas.showImage(grabber.grab());//Get the camera image and display it on the window, where Frame frame=grabber.grab(); frame is a frame of video image
            }
            recorder.stop();
            grabber.stop();
            System.out.println("ssssss");

        } finally{
            if (grabber != null) {
                grabber.stop();
            }
        }
    }
}
