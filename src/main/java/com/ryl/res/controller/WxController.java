package com.ryl.res.controller;

import com.ryl.res.service.IWxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-30 12:47:37
 */
@Controller
@RequestMapping("/wechat")
@Api(tags = "微信登录")
public class WxController {

    @Autowired
    private IWxService iWxService;

    @ApiOperation(value = "微信授权登录", notes = "微信授权登录,生成二维码")
    @GetMapping("/login")
    @ResponseBody
    public String wechatLogin() throws Exception {
        String url = iWxService.getQRcodeUrl();
        return "redirect:" + url;
    }


    @ApiOperation(value = "微信授权登录", notes = "微信授权登录,生成二维码")
    @GetMapping("/aa")
    @ResponseBody
    public String hello() {
        return "hello";
    }

//    @ApiOperation(value = "微信授权回调", notes = "扫码回调接口")
//    @PostMapping("/wechat/callback")
//    public void wechatCallback(HttpServletRequest request){
//        String state = request.getParameter("state");
//        String code = request.getParameter("code");
//        if (code == null) {
//            throw new RuntimeException("授权失败");
//        }
//        iWxService.loginCallback();
//        //1.code获取accessToken
//        Map<String,String> accParams = new HashMap<>();
//        accParams.put("appid",WxConfigConstant.APP_ID);
//        accParams.put("secret",WxConfigConstant.APP_SECRET);
//        accParams.put("code",code);
//        accParams.put("grant_type", WxConfigConstant.AUTH_GRANT_TYPE);
//        ResponseEntity<String> accessRes = restTemplate.getForEntity(WxApiUrlConstant.CODE_2_ACCESS_TOKEN_URL, String.class, accParams);
//        JSONObject accessObj = JSON.parseObject(accessRes.getBody());
//        String accessToken = (String) accessObj.get("access_token");
//        String openId = (String) accessObj.get("openid");
//
//        //2.调用用户个人信息接口获取用户信息
//        Map<String,String> userParams = new HashMap<>();
//        userParams.put("access_token",accessToken);
//        userParams.put("openid",openId);
//        ResponseEntity<String> userRes = restTemplate.getForEntity(WxApiUrlConstant.USER_INFO_URL, String.class, userParams);
//        JSONObject userObj = JSON.parseObject(userRes.getBody());
//
//    }
}
