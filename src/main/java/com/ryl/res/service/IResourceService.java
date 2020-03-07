package com.ryl.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryl.res.model.entity.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-02-28 19:02:31
 */
public interface IResourceService extends IService<Resource> {

    List<String> uploadFile(MultipartFile[] multipartFiles);

    List<Resource> userList();
}
