package com.jason.common.utils;

import java.util.UUID;

public class UUIDUtil {
    /**
     * 生成无横线的 UUID
     * @return 无横线的 UUID 字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
