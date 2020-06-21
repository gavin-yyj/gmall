package com.gavin.gmall.util;

import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;
import org.csource.fastdfs.ClientGlobal;

import java.io.IOException;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 12:25 2020/6/21
 * @Description:
 */

public class PmsUploadUtil {
    public static String uploadImage(MultipartFile multipartFile) {

        String imgUrl = "http://192.168.37.132";

        //上传图片到服务器
        //配置fdfs的全局连接地址
        //获得配置文件的路径
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();

        try {
            ClientGlobal.init(tracker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TrackerClient trackerClient = new TrackerClient();

        // 获得一个trackerServer的实例
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // 通过tracker获得一个Storage链接客户端
        StorageClient storageClient = new StorageClient(trackerServer, null);

        try {
            //获得上传的图片二进制对象
            byte[] bytes = multipartFile.getBytes();
            //获得文件后缀名
            String filename = multipartFile.getOriginalFilename();
            int i = filename.lastIndexOf(".");
            String extName = filename.substring(i+1);
            String[] upload_file = storageClient.upload_file(bytes, extName, null);

            for (String uploadInfo : upload_file) {
                imgUrl += "/" + uploadInfo;
            }
        } catch (Exception e1) {
            e1.printStackTrace();

        }

        //返回图片地址
        return imgUrl;
    }
}
