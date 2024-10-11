package com.jason.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.common.model.system.SystemConfig;

import java.util.HashMap;
import java.util.List;

public interface SystemConfigService extends IService<SystemConfig> {

    /**
     * 根据menu name 获取 value
     * @param key menu name
     * @return String
     */
    String getValueByKey(String key);
}
