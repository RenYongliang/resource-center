package com.ryl.res.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryl.res.config.obs.ObsClientUtil;
import com.ryl.res.mapper.ResourceMapper;
import com.ryl.res.model.entity.Resource;
import com.ryl.res.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public List<String> uploadFile(MultipartFile[] multipartFiles) {
        return obsClientUtil.parallelUpload(multipartFiles);
    }

    @Override
    public List<Resource> userList() {
        String key = "user";
        List<Resource> list;
        //取缓存
        if (redisTemplate.hasKey(key)) {
            String json = redisTemplate.opsForValue().get(key);
            list = JSONObject.parseArray(json, Resource.class);
        } else {
            //取db
            list = this.list();
            //存缓存
            redisTemplate.opsForValue().set(key,JSONObject.toJSONString(list));
        }
        return list;
    }


}
