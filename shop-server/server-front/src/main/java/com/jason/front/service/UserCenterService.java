package com.jason.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.common.model.user.User;
import com.jason.common.request.*;
import com.jason.common.response.*;
import com.jason.common.vo.MyRecord;

import java.math.BigDecimal;
import java.util.List;


public interface UserCenterService extends IService<User> {

    /**
     * 通过微信code登录
     */
    LoginResponse weChatAuthorizeProgramLogin(String code);

    /**
     * 小程序获取授权logo
     * @return String
     */
    String getLogo();


    LoginResponse weChatRegister(RegisterRequest registerRequest);
}
