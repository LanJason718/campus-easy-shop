package com.jason.service.service;


import com.jason.common.vo.WeChatMiniAuthorizeVo;

public interface WechatNewService {


    /**
     * 小程序登录凭证校验
     *
     * @return 小程序登录校验对象
     */
    WeChatMiniAuthorizeVo miniAuthCode(String code);

}
