package com.ryl.res.controller;

import com.ryl.res.config.jwt.JwtTokenUtil;
import com.ryl.res.config.jwt.JwtUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-03 09:56:18
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public String login(String username,String password){
        //数据库记录返回userId
        Long userId = 999L;
        return JwtTokenUtil.generateJwtToken(userId.toString());
    }

    @PostMapping("/getJwtUser")
    @ApiOperation("解析token")
    public JwtUser getJwtUser(String token){
        JwtUser jwtUser = JwtTokenUtil.parseJwtToken(token);
        return jwtUser;
    }
}
