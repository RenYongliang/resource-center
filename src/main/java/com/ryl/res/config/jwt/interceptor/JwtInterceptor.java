package com.ryl.res.config.jwt.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
//        //请求接口
//        String reqPath = request.getServletPath();
//        //Header
//        String authHeader = request.getHeader(HEADER_KEY);
//        if(authHeader == null) {
//            response.setStatus(901);
//            return false;
//        }
//        if(!authHeader.startsWith(TOKEN_PREFIX_TYPE)) {
//            response.setStatus(801);
//            return false;
//        }
//
//        //解析token
//        Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(authHeader.substring(TOKEN_PREFIX_TYPE.length()));

        return true;
    }
}
