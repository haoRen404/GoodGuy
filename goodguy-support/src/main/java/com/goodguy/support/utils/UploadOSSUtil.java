package com.goodguy.support.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云oss工具类
 * 注意：不要shutdown()，因为ossClient是单列的，如果关闭了，那下一次使用ossClient就会报错“oss Connection pool shut down”
 *      参考博客:https://blog.csdn.net/faker1234546/article/details/124856252
 *      博客中说了两种解决方式，每次使用之前new一个，或者配置成多例
 *      另一篇博客：https://www.cnblogs.com/ukzq/p/12082414.html
 *
 */
@Component
public class UploadOSSUtil {

    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucketName;

    @Autowired
    private OSS ossClient;


    /**
     *
     * @param inputStream 输入流
     * @param newFileName oss中的名字
     * @param originalFileName 原始文件名
     * @return
     */
    public PutObjectResult uploadFile(InputStream inputStream, String newFileName, String originalFileName) {
        try {
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(inputStream.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("attachment;filename=\"" + originalFileName + "\"");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, newFileName, inputStream, metadata);
            return putObjectResult;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭ossClient
            // ossClient.shutdown();
        }
        return null;
    }

    /**
     * 上传文件。可自定义文件下载时的文件名
     * @param file
     * @param folder
     * @param oldName 自定义文件下载时的文件名
     * @return
     */
    public String uploadFile(File file, String folder, String oldName) {
        String fileName = file.getName();

        String url = null;
        try {
            // 以输入流的形式上传文件
            InputStream is = new FileInputStream(file);
            // 文件名
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileName;
            // 文件大小
            Long fileSize = file.length();
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            // metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("attachment;filename=\"" + oldName + "\"");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
            // 解析结果
            url = folder + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭ossClient
            // ossClient.shutdown();
        }
        return url;
    }

    /**
     * 获取用户上传文件时指定的前缀
     * @return
     */
    public String getDir() {
        // 获取用户上传文件时指定的前缀
        String dir = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
        return dir;
    }

    /**
     * 获取文件下载链接
     * @param path
     * @return
     */
    public String getDownloadUrl(String path) {
        // 设置URL过期时间为1小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, path, expiration);
        // 关闭OSSClient。
        // ossClient.shutdown();
        return url.toString();
    }
}
