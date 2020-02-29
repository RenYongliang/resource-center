package com.ryl.res.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author: ryl
 * @description: 华为云obsClient工具类
 * @date: 2020-02-29 18:36:18
 */
public class ObsClientUtil {

    @Value("${huaweiyun.obs.access-key}")
    private String accessKey;

    @Value("${huaweiyun.obs.secret-key}")
    private String secretKey;

    @Value("${huaweiyun.obs.end-point}")
    private String endPoint;

}
