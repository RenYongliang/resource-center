package com.ryl.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryl.res.mapper.ResourceMapper;
import com.ryl.res.model.entity.Resource;
import com.ryl.res.service.IResourceService;
import org.springframework.stereotype.Service;

/**
 * @author: ryl
 * @description:
 * @date: 2020-02-28 19:02:58
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper,Resource> implements IResourceService {
}
