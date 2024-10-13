package com.jason.common.vo;

import lombok.Data;

/**
 * 文件信息
 */
@Data
public class FileResultVo {

    // 文件名
    private String fileName;

    // 扩展名
    private String extName;

    // 文件大小，字节
    private Long fileSize;

    //可供访问的url 域名根据配置存储
    private String url;

    //类型
    private String type;
}
