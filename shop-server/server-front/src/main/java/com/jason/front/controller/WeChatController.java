package com.jason.front.controller;

import com.jason.common.response.CommonResult;
import com.jason.common.response.LoginResponse;
import com.jason.front.service.UserCenterService;
import com.jason.service.service.WechatNewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController("WeChatFrontController")
@RequestMapping("/api/front/wechat")
@Api(tags = "微信 -- 开放平台")
public class WeChatController {

    @Autowired
    private UserCenterService userCenterService;


    /**
     * 微信登录小程序授权登录
     */
    @ApiOperation(value = "微信小程序授权登录")
    @GetMapping("/authorize/program/login")
    public CommonResult programLogin(@RequestParam("code") String code){
        return CommonResult.success(userCenterService.weChatAuthorizeProgramLogin(code));
    }


    /**
     * 小程序获取授权logo
     */
    @ApiOperation(value = "小程序获取授权logo")
    @GetMapping("/getLogo")
    public CommonResult<Map<String, String>> getLogo(){
        Map<String, String> map = new HashMap<>();
        map.put("logoUrl", userCenterService.getLogo());
        return CommonResult.success(map);
    }


}



