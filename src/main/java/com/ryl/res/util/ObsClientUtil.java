package com.ryl.res.util;

import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import com.ryl.res.enums.FileTypeEnum;
import com.ryl.res.model.entity.Resource;
import com.ryl.res.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: ryl
 * @description: 华为云obsClient工具类
 * @date: 2020-02-29 18:36:18
 */
@Slf4j
@Component
public class ObsClientUtil {

    @Autowired
    IResourceService iResourceService;
    @Autowired
    private ObsClient obsClient;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;
    @Value("${huaweiyun.obs.bucket-name}")
    private String bucketName;


    /**
     * 并行批量上传文件
     * @param multipartFiles
     * @return
     */
    public List<String> parallelUpload(MultipartFile[] multipartFiles) {
        List<String> urlList = new Vector<>();
        List<Resource> resourceList = new Vector<>();
        ExecutorService es = Executors.newCachedThreadPool();
        for (MultipartFile file : multipartFiles) {
            es.execute(() -> {
                //文件名
                String fileName = file.getOriginalFilename();
                //文件后缀名
                String[] arr = fileName.split("\\.");
                String fileSuffix = arr[arr.length-1];
                //文件类型
                int fileType = getFileType(fileSuffix);
                //唯一key
                Long fileKey = snowflakeIdGenerator.nextId();
                //objectKey
                String objectKey = new StringBuilder(fileKey.toString())
                        .append("-")
                        .append(fileName)
                        .toString();
                PutObjectResult result;
                try {
                    result = obsClient.putObject(bucketName, objectKey, file.getInputStream());
                } catch (IOException e) {
                    log.error("获取文件流异常,文件名:{},文件唯一key:{}", fileName, fileKey);
                    throw new RuntimeException("获取文件流异常,文件名:" + fileName);
                }
                urlList.add(result.getObjectUrl());

                Resource resource = new Resource();
                resource.setFileName(fileName)
                        .setFileType(fileType)
                        .setObjectKey(objectKey)
                        .setResourceUrl(result.getObjectUrl());

                resourceList.add(resource);
            });
        }
        //等待所有子线程执行完毕
        while (urlList.size() < multipartFiles.length){}
        //记录插库
        iResourceService.saveBatch(resourceList);
        return urlList;
    }


    /**
     * 获取文件类型
     * @param suffix
     * @return
     */
    private int getFileType(String suffix) {
        suffix = suffix.toUpperCase();
        for (FileTypeEnum fileTypes : FileTypeEnum.values()) {
            if (fileTypes.getTypeList().contains(suffix)) {
                return fileTypes.getType();
            }
        }
        return FileTypeEnum.OTHER_TYPE.getType();
    }
}
