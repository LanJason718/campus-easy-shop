package com.jason.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "shop")
public class ShopConfig {
    // 当前代码版本
    private String version;
    // 是否同步config表数据到redis
    private boolean asyncConfig;
    // 本地图片路径配置
    private String imagePath;
}
