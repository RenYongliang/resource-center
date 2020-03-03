package com.ryl.res.config.obs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: ryl
 * @description: 华为云OBS 参数配置
 * @date: 2020-03-02 16:41:15
 */
@Data
@ConfigurationProperties(prefix = "huaweiyun.obs")
@Component
public class ObsConfigParam {

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * 终端
     */
    private String endPoint;
}
