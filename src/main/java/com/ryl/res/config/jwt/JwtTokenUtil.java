package com.ryl.res.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ryl.res.config.jwt.JwtProperties.*;

/**
 * @author: ryl
 * @description: JWT token 工具类
 * @date: 2020-03-03 09:21:07
 */
public class JwtTokenUtil {

    /**
     * 传userId生成token
     * @param userGuid
     * @return
     */
    public static String generateJwtToken(String userGuid) {
        // expireDate 7天
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, EXPIRE_TIME);
        calendar.add(Calendar.SECOND, 10);
        Date expireDate = calendar.getTime();
        //载荷信息 存放user对象
        Map<String, Object> claimsMap = new HashMap<>(1);
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(userGuid);
        claimsMap.put(CLAIMS_KEY, jwtUser);
        String jwt = Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(JWT_SUBJECT)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return new StringBuilder().append(TOKEN_PREFIX_TYPE).append(" ").append(jwt).toString();
    }

    /**
     * 传jwtUser生成token
     * @param jwtUser
     * @return
     */
    public static String generateJwtToken(JwtUser jwtUser) {
        // expireDate 7天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, EXPIRE_TIME);
        Date expireDate = calendar.getTime();
        //载荷信息 存放user对象
        Map<String, Object> claimsMap = new HashMap<>(1);
        claimsMap.put(CLAIMS_KEY, jwtUser);
        String jwt = Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(JWT_SUBJECT)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return new StringBuilder().append(TOKEN_PREFIX_TYPE).append(" ").append(jwt).toString();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static JwtUser parseJwtToken(String token){
        //去除前缀 Bearer
        token = token.substring(7);
        Object o = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get(CLAIMS_KEY);
        //转成JwtUser对象
        return new ObjectMapper().convertValue(o,JwtUser.class);
    }
}
