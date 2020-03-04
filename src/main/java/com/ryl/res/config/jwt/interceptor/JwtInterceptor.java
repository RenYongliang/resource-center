package com.ryl.res.config.jwt.interceptor;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ryl.res.config.jwt.JwtProperties.SECRET;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-04 20:58:19
 */
@Slf4j
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求接口
        String reqPath = request.getServletPath();
        //Header
        String authHeader = request.getHeader("Authorization");
//        if(authHeader == null) {
//            return false;
//        }
//        if(!authHeader.startsWith("Bearer ")) {
//            return false;
//        }

        //解析token
        Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(authHeader.substring(7));

        return true;
    }
}
