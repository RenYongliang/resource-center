package com.ryl.res.config.jwt;

/**
 * @author: ryl
 * @description: jwt参数配置类
 * @date: 2020-03-03 09:26:39
 */
public class JwtProperties {

    /**
     *  存放Token的Header Key
     */
    public static final String HEADER_KEY = "Authorization";

    /**
     * 密匙key
     */
    public static final String SECRET = "3306wdnss^rylCoder";

    /**
     * 过期时间 7天
     */
    public static final int EXPIRE_TIME = 7;

    /**
     * header token 前缀
     */
    public static final String TOKEN_PREFIX_TYPE = "Bearer ";

    /**
     * playLoad 载荷信息KEY
     */
    public static final String CLAIMS_KEY = "user";

    /**
     * JWT主体信息 区分微信端和管理端
     */
    public static final String JWT_SUBJECT = "ryl_jwt_subject";
}
