package com.ryl.res.controller;

import com.ryl.framework.base.ResultModel;
import com.ryl.framework.jwt.JwtUser;
import com.ryl.framework.jwt.JwtUtil;
import com.ryl.res.model.entity.Resource;
import com.ryl.res.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResultModel<String> login(){
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId("999");
        jwtUser.setUsername("zhangsan");
        String s = JwtUtil.generateJwtToken(jwtUser);
//        throw new ServiceException(ServiceExceptionEnum.SPU_NOT_FOUND.getCode(),ServiceExceptionEnum.SPU_NOT_FOUND.getMessage());
        return ResultModel.success(s);
    }

    @PostMapping("/getJwtUser")
    @ApiOperation("解析token")
    public ResultModel<JwtUser> getJwtUser(HttpServletRequest request){
        JwtUser jwtUser = JwtUtil.getJwtUser(request);
        return ResultModel.success(jwtUser);
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
