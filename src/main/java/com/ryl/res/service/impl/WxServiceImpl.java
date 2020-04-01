package com.ryl.res.service.impl;

import com.ryl.res.enums.constants.WxApiUrlConstant;
import com.ryl.res.enums.constants.WxConfigConstant;
import com.ryl.res.service.IWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-30 13:54:46
 */
@Service
public class WxServiceImpl implements IWxService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public String getQRcodeUrl() throws UnsupportedEncodingException {
        String url = WxApiUrlConstant.QRCONNECT_URL;
        String callBack = "http://v2197366e0.iask.in/wechat/callback";
        String redirectUri = URLEncoder.encode(callBack,"utf-8");
        url = url.replace("APPID", WxConfigConstant.APP_ID).replace("REDIRECT_URI",redirectUri).replace("SCOPE",WxConfigConstant.AUTH_CODE_SCOPE);
        return url;
    }
}
