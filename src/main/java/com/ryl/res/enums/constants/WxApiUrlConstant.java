package com.ryl.res.enums.constants;


/**
 * @author: ryl
 * @description:
 * @date: 2020-03-30 12:47:37
 */
public class WxApiUrlConstant {

    /**
     * 微信扫码登录获取二维码url
     */
    public static final String QRCONNECT_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    /**
     * 通过code获取access_token 请求url
     * GET
     */
    public static final String CODE_2_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 获取用户个人信息 请求url
     * GET
     */
    public static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

}
