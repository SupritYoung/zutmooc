package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.*;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.Utils.ConstantVodUtils;
import com.atguigu.vod.Utils.InitVodCilent;
import com.atguigu.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
//@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId", videoId);
    }


    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id) {
        System.out.println("开始删除视频" + id);
        System.out.println(ConstantVodUtils.ACCESS_KEY_SECRET);
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            System.out.println("删除视频" + id + "成功！");
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id) {
        try {
            //创建初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(id);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);


            System.out.println(client.getProfile().getCertPath());
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth", playAuth);
        } catch (Exception e) {
            throw new GuliException(20001, "获取凭证失败");
        }
    }

    //根据视频id获取视频地址
        @GetMapping("getFileURL/{id}")
    public R getFileURL(@PathVariable String id) {
        String fileURL = "";
        try {
            //创建初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetMezzanineInfoRequest request = new GetMezzanineInfoRequest();
            //向request设置视频id
            request.setVideoId(id);
            //设置过期时间
            request.setAuthTimeout(3600L);
            GetMezzanineInfoResponse response = new GetMezzanineInfoResponse();
            response = client.getAcsResponse(request);

            fileURL = response.getMezzanine().getFileURL();
            System.out.print("Mezzanine.FileURL = " + fileURL + "\n");
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return R.ok().data("fileURL", fileURL);
    }

}
