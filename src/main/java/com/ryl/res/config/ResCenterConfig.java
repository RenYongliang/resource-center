package com.ryl.res.config;

import com.obs.services.ObsClient;
import com.ryl.res.util.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ryl
 * @description:
 * @date: 2020-02-28 19:29:24
 */
@Configuration
public class ResCenterConfig {

    @Bean
    public ObsClient obsClient(ObsConfigParam configParam){
        return new ObsClient(configParam.getAccessKey(),configParam.getSecretKey(),configParam.getEndPoint());
    }

    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator() {
        return new SnowflakeIdGenerator(0, 0);
    }

}
