package com.ryl.res.config;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-02 16:41:15
 */
@ApiModel("obs参数")
@Data
@ConfigurationProperties(prefix = "huaweiyun.obs")
@Component
public class ObsConfigParam {

//    @Value("${huaweiyun.obs.access-key}")
//    private String accessKey;
//
//    @Value("${huaweiyun.obs.secret-key}")
//    private String secretKey;
//
//    @Value("${huaweiyun.obs.end-point}")
//    private String endPoint;

    private String accessKey;

    private String secretKey;

    private String endPoint;
}
