package com.atguigu.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.impl.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFile(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //上传文件流。
            InputStream inputStream = file.getInputStream();
            //调用OSS方法实现上传

            //生成唯一随机数，防止重名覆盖
            String uuid = UUID.randomUUID().toString();

            //按照日期生成分类文件夹
            String datePath = new DateTime().toString("yyyy/MM/dd").replace("/","");

            String fileName = file.getOriginalFilename();
            //获取上传后的路径（手动拼接）
            fileName = datePath+"/"+uuid+fileName;
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
