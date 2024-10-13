package com.jason.front.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.common.exception.ShopException;
import com.jason.common.request.RegisterRequest;
import com.jason.common.token.FrontTokenComponent;
import com.jason.common.utils.RedisUtil;
import com.jason.common.vo.WeChatMiniAuthorizeVo;
import com.jason.common.model.user.User;
import com.jason.common.response.LoginResponse;
import com.jason.common.utils.JwtUtil;
import com.jason.front.service.UserCenterService;
import com.jason.service.dao.UserDao;
import com.jason.service.service.WechatNewService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserCenterServiceImpl extends ServiceImpl<UserDao, User> implements UserCenterService {
    private final Logger logger = LoggerFactory.getLogger(UserCenterServiceImpl.class);

    @Autowired
    WechatNewService wechatNewService;
    @Autowired
    private FrontTokenComponent tokenComponent;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public LoginResponse weChatAuthorizeProgramLogin(String code) {
        // 调用微信服务获取 openId 和 sessionKey
        WeChatMiniAuthorizeVo response = wechatNewService.miniAuthCode(code);
        System.out.println("小程序登陆成功 = " + JSON.toJSONString(response));
        // 检查用户是否存在，根据 openId 查询数据库
        User user = this.getOne(new QueryWrapper<User>().eq("open_id", response.getOpenId()));
        LoginResponse loginResponse = new LoginResponse();
        if (ObjectUtil.isNotNull(user)) {// 已存在，正常登录
            if (!user.getStatus()) {
                throw new ShopException("当前账户已禁用，请联系管理员！");
            }
            try {
                String token = tokenComponent.createToken(user);
                loginResponse.setToken(token);
            } catch (Exception e) {
                logger.error(StrUtil.format("小程序登录生成token失败，uid={}", user.getUid()));
                e.printStackTrace();
            }
            loginResponse.setType("login");
            loginResponse.setUid(user.getUid());
            loginResponse.setNikeName(user.getNickname());
            loginResponse.setPhone(user.getPhone());
            return loginResponse;
        }
        //返回注册响应
        String key = SecureUtil.md5(response.getOpenId());
        redisUtil.set(key, JSON.toJSONString(response),(long) (60 * 2), TimeUnit.MINUTES);
        loginResponse.setType("register");
        loginResponse.setKey(key);
        return loginResponse;
    }

    @Override
    public String getLogo() {
        return "";
    }

    @Override
    public LoginResponse weChatRegister(RegisterRequest request) {
        Object o = redisUtil.get(request.getKey());
        if (ObjectUtil.isNull(o)) {
            throw new ShopException("用户缓存已过期，请清除缓存重新登录");
        }
        WeChatMiniAuthorizeVo au = JSONObject.parseObject(o.toString(), WeChatMiniAuthorizeVo.class);

        User user = new User();
        user.setNickname(request.getNickName());
        user.setAvatar(request.getAvatar());
        user.setOpenId(au.getOpenId());
        this.save(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setType("login");
        loginResponse.setUid(user.getUid());
        loginResponse.setNikeName(user.getNickname());
        loginResponse.setPhone(user.getPhone());
        return loginResponse;
    }
}