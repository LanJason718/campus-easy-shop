package com.jason.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.common.exception.ShopException;
import com.jason.common.model.system.SystemConfig;
import com.jason.service.dao.SystemConfigDao;
import com.jason.service.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import javax.annotation.Resource;
import com.jason.common.config.ShopConfig;
import com.jason.common.utils.RedisUtil;
import java.util.List;
import com.jason.common.constants.Constants;
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigDao, SystemConfig> implements SystemConfigService {

    @Resource
    private SystemConfigDao dao;
    @Autowired
    private ShopConfig shopConfig;
    @Autowired
    private RedisUtil redisUtil;

    private static final String redisKey = Constants.CONFIG_LIST;

    /**
     * 根据menu name 获取 value
     *
     * @param name menu name
     * @return String
     */
    @Override
    public String getValueByKey(String name) {
        return get(name);
    }

    /**
     * 把数据同步到redis
     *
     * @param name String
     * @return String
     */
    private String get(String name) {
        if (!shopConfig.isAsyncConfig()) {
            //如果配置没有开启
            LambdaQueryWrapper<SystemConfig> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SystemConfig::getStatus, false).eq(SystemConfig::getName, name);
            SystemConfig systemConfig = dao.selectOne(lambdaQueryWrapper);
            if (ObjectUtil.isNull(systemConfig) || StrUtil.isBlank(systemConfig.getValue())) {
                return "";
            }
            return systemConfig.getValue();
        }
        setRedisByVoList();
        Object data = redisUtil.hmGet(redisKey, name);
        if (ObjectUtil.isNull(data) || StrUtil.isBlank(data.toString())) {
            //没有找到数据
            return "";
        }
        //去数据库查找，然后写入redis
        return data.toString();
    }

    /**
     * 把数据同步到redis, 此方法适用于redis为空的时候进行一次批量输入
     */
    private void setRedisByVoList() {
        //检测redis是否为空
        Long size = redisUtil.getHashSize(redisKey);
        if (size > 0 || !shopConfig.isAsyncConfig()) {
            return;
        }

        LambdaQueryWrapper<SystemConfig> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemConfig::getStatus, false);
        List<SystemConfig> systemConfigList = dao.selectList(lambdaQueryWrapper);
        async(systemConfigList);
    }

    /**
     * 把数据同步到redis
     * @param systemConfigList List<SystemConfig> 需要同步的数据
     */
    private void async(List<SystemConfig> systemConfigList) {
        if (!shopConfig.isAsyncConfig()) {
            //如果配置没有开启
            return;
        }
        for (SystemConfig systemConfig : systemConfigList) {
            redisUtil.hmSet(redisKey, systemConfig.getName(), systemConfig.getValue());
        }
    }

    /**
     * 根据 name 获取 value 找不到抛异常
     * @param name menu name
     * @return String
     */
    @Override
    public String getValueByKeyException(String name) {
        String value = get(name);
        if (null == value) {
            throw new ShopException("没有找到"+ name +"数据");
        }

        return value;
    }
}

