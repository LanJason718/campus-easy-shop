package com.jason.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.jason.common.constants.WeChatConstants;
import com.jason.common.exception.ShopException;
import com.jason.common.utils.RestTemplateUtil;
import com.jason.service.service.SystemConfigService;
import com.jason.service.service.WechatNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jason.common.vo.WeChatMiniAuthorizeVo;

@Service
public class WechatNewServiceImpl implements WechatNewService {

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    /**
     * 小程序登录凭证校验
     * @return 小程序登录校验对象
     */
    @Override
    public WeChatMiniAuthorizeVo miniAuthCode(String code) {
        String appId = systemConfigService.getValueByKey(WeChatConstants.WECHAT_MINI_APPID);
        if (StrUtil.isBlank(appId)) {
            throw new ShopException("微信小程序appId未设置");
        }
        String secret = systemConfigService.getValueByKey(WeChatConstants.WECHAT_MINI_APPSECRET);
        if (StrUtil.isBlank(secret)) {
            throw new ShopException("微信小程序secret未设置");
        }
        String url = StrUtil.format(WeChatConstants.WECHAT_MINI_SNS_AUTH_CODE2SESSION_URL, appId, secret, code);
        JSONObject data = restTemplateUtil.getData(url);
        if (ObjectUtil.isNull(data)) {
            throw new ShopException("微信平台接口异常，没任何数据返回！");
        }
        if (data.containsKey("errcode") && !"0".equals(data.getString("errcode"))) {
            if (data.containsKey("errmsg")) {
                throw new ShopException("微信接口调用失败：" + data.getString("errcode") + data.getString("errmsg"));
            }
        }
        return JSONObject.parseObject(data.toJSONString(), WeChatMiniAuthorizeVo.class);
    }

}
