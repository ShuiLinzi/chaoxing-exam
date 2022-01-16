package com.shui.exam.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.shui.exam.service.OssService;
import com.shui.exam.utils.OssKey;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Autowired
    private OssKey ossKey;

    public String upload(InputStream inputStream, String module, String originalFilename) {
        String endpoint = ossKey.getEndpoint();
        String keyid = ossKey.getAccessKeyId();
        String keysecret = ossKey.getAccessKeySecret();
        String bucketname = ossKey.getBucketName();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        //判断是否存在此bucket
        if (!ossClient.doesBucketExist(bucketname)) {
            ossClient.createBucket(bucketname);
            //设置bucket的权限为公共读
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }
        //构建oss里面的日期路径
        String time = new DateTime().toString("yyyy/MM/dd");
        //拼写图片的名字
        String fileName = UUID.randomUUID().toString();
        //文件的后缀格式
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String key = module + "/" + time + "/" + fileName + substring;


        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketname, key, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://" + bucketname + "." + endpoint + "/" + key;
    }
}
