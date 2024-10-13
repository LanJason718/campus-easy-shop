package com.jason.service.service;


import com.jason.common.vo.CloudVo;

import java.io.File;

public interface OssService {

    void upload(CloudVo cloudVo, String webPth, String localFile, File file);

}
