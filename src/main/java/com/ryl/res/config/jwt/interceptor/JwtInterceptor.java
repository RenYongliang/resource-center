package com.ryl.res.config.jwt.interceptor;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static com.ryl.res.config.jwt.JwtProperties.*;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-04 20:58:19
 */
@Slf4j
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //请求接口名称
        String reqPath = request.getServletPath();
        //在白名单内就放行
        String[] urls = JWT_WHITE_LIST.split(";");
        if (Arrays.asList(urls).contains(reqPath)) {
            return true;
        }
        //Header
        String authHeader = request.getHeader(HEADER_KEY);
        if(authHeader == null) {
            throw new RuntimeException("request header doesn't contain authorization information...");
        }
        if(!authHeader.startsWith(TOKEN_PREFIX_TYPE)) {
            throw new RuntimeException("not a standard JwtToken format...");
        }

        //解析token
        Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(authHeader.substring(TOKEN_PREFIX_TYPE.length()));

        return true;
    }
}
