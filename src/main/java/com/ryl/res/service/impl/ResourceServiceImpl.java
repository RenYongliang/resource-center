package com.ryl.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryl.res.mapper.ResourceMapper;
import com.ryl.res.model.entity.Resource;
import com.ryl.res.service.IResourceService;
import com.ryl.res.config.obs.ObsClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-02-28 19:02:58
 */
@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper,Resource> implements IResourceService {

    @Autowired
    private ObsClientUtil obsClientUtil;


    @Override
    public List<String> uploadFile(MultipartFile[] multipartFiles) {
        return obsClientUtil.parallelUpload(multipartFiles);
    }
}
