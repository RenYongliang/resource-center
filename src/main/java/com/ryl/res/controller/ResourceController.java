package com.ryl.res.controller;

import com.ryl.res.model.entity.Resource;
import com.ryl.res.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-02-28 19:02:17
 */
@RestController
@Api(tags = "资源操作接口")
@RequestMapping("/resource")
@Slf4j
public class ResourceController {

    @Autowired
    IResourceService iResourceService;

    @PostMapping("/hello")
    @ApiOperation("hello接口")
    public List<Resource> hello(){
        List<Resource> list = iResourceService.lambdaQuery().list();
        return list;
    }
}
