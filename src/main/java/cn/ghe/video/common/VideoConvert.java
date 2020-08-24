package cn.ghe.video.common;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

public class VideoConvert {
    public static void main(String[] args) throws Exception {
        VideoConvert m = new VideoConvert();
        String sourcePath = "C:\\Users\\17426\\Downloads\\123遇见·泸州老窖_Final版.mp4";
        String targetPath = "C:\\Users\\17426\\Downloads\\Final版333.mp4";
        m.frameRecord(sourcePath, targetPath);
    }
    private boolean isStart = true;

    public void frameRecord(String inputFile, String outputFile)
            throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        // Get the video source
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        //grabber.setOption("rtsp_transport","tcp");
        //speed
        grabber.setFrameRate(30);
        // code rate
        grabber.setVideoBitrate(3000000);
        // Streaming media output address, resolution (length, height), whether to record audio (0: no recording / 1: recording)
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, 4096, 2160, 1);
        recorder.setFrameRate(30);
        recorder.setVideoBitrate(4000000);
        //Encoding format
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        System.out.println("32232");
        //avcodec.AV_CODEC_ID_H264  //AV_CODEC_ID_MPEG4
        recordByFrame(grabber, recorder);
    }

    private void recordByFrame(FFmpegFrameGrabber grabber, FFmpegFrameRecorder recorder)
            throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        try {// it is recommended to use this method in a thread

            grabber.start();
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
