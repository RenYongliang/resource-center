package com.ryl.res.controller;

import com.ryl.res.config.jwt.JwtTokenUtil;
import com.ryl.res.config.jwt.JwtUser;
import com.ryl.res.model.entity.Resource;
import com.ryl.res.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-03 09:56:18
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private IResourceService iResourceService;

    private static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @GetMapping("/login")
    @ApiOperation("用户登录")
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password){
        String token = request.getHeader("authorization");
        response.setHeader("name","ryl");
        B_CRYPT_PASSWORD_ENCODER.encode(password);//密码加密
        //数据库记录返回userId
        Long userId = 999L;
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername("asdbas");
        jwtUser.setLocalDateTime(LocalDateTime.now());
        return JwtTokenUtil.generateJwtToken(jwtUser);
    }

    @PostMapping("/getJwtUser")
    @ApiOperation("解析token")
    public JwtUser getJwtUser(HttpServletRequest request){
        JwtUser jwtUser = JwtTokenUtil.getJwtUser(request);
        return jwtUser;
    }

    @PostMapping("/list")
    @ApiOperation("用户列表")
    public List<Resource> list(){
        return iResourceService.userList();
    }


    public static void main(String[] args) {
        System.out.println();
    }

}
