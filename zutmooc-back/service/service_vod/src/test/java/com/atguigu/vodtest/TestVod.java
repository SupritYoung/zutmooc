package com.atguigu.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.atguigu.vod.controller.VodController;
import org.junit.Test;

import java.util.List;

public class TestVod {
    public String accessKeyId = "LTAI5tQo5uaxSXXXa3AyUQKF";
    public String accessKeySecret = "NLmaV2tOUzf48ykGFVsGEUKr8GlWgg";

    //测试获取播放地址
    @Test
    public void getPlayUrl() throws ClientException {
        //根据视频id获取播放地址

        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tQo5uaxSXXXa3AyUQKF","NLmaV2tOUzf48ykGFVsGEUKr8GlWgg");

        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request中设置视频id
        request.setVideoId("a67e52563fa3433295ddb579f6196022");

        //调用初始化对象中的方法，传递request，获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

    //测试获取播放凭证
    @Test
    public void getPlayAuth() throws ClientException {
        //根据视频id获取播放地址

        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tQo5uaxSXXXa3AyUQKF","NLmaV2tOUzf48ykGFVsGEUKr8GlWgg");

        //创建获取视频地址request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        //向request中设置视频id
        request.setVideoId("8b79cd72921f4ac9bbe85cc94bc691fa");

        //调用初始化对象中的方法，传递request，获取数据
        response = client.getAcsResponse(request);
        System.out.println("playAuth:"+response.getPlayAuth());
    }

    @Test
    public void testUploadVideo() {
        String accessKeyId = "LTAI5tQo5uaxSXXXa3AyUQKF";
        String accessKeySecret = "NLmaV2tOUzf48ykGFVsGEUKr8GlWgg";
        String title = "testupload";           //上传后文件名称
        String fileName = "/Volumes/ssd2/学习资料/Java/在线教育--谷粒学院/项目资料/1-阿里云上传测试视频/6 - What If I Want to Move Faster.mp4";        //本地文件路径+名称

        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    @Test
    public void testRemoveVideo(){
        VodController vodController = new VodController();
        vodController.removeAlyVideo("51911e6dafc04d4f8598b1aecd67eb83");

    }

}
