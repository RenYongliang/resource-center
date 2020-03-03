package com.ryl.res.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ryl.res.config.jwt.JwtConfigParam.*;

/**
 * @author: ryl
 * @description: JWT token 工具类
 * @date: 2020-03-03 09:21:07
 */
@Component
public class JwtTokenUtil {

    /**
     * 传userId生成token
     * @param userGuid
     * @return
     */
    public static String generateJwtToken(String userGuid) {
        // expireDate 7天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, EXPIRE_TIME);
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
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
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
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();
        return new StringBuilder().append(TOKEN_PREFIX_TYPE).append(" ").append(jwt).toString();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static JwtUser parseJwtToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(TOKEN_SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return (JwtUser) claims.get(CLAIMS_KEY);
    }
}
