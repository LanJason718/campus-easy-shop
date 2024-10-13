package com.jason.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.common.constants.Constants;
import com.jason.common.model.system.SystemAttachment;
import com.jason.service.dao.SystemAttachmentDao;
import com.jason.service.service.SystemAttachmentService;
import com.jason.service.service.SystemConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SystemAttachmentServiceImpl 接口实现
*/
@Service
public class SystemAttachmentServiceImpl extends ServiceImpl<SystemAttachmentDao, SystemAttachment>
        implements SystemAttachmentService {

    @Resource
    private SystemAttachmentDao dao;

    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * 同步到云服务， 更新图片上传类型
     * @param attId Integer 主键id
     * @param type int 图片上传类型 1本地 2七牛云 3OSS 4COS
     */
    @Override
    public void updateCloudType(Integer attId, int type) {
        SystemAttachment systemAttachment = new SystemAttachment();
        systemAttachment.setAttId(attId);
        systemAttachment.setImageType(type);
        updateById(systemAttachment);
    }


    /**
     * 给图片加前缀
     * @param path String 路径
     * @return String
     */
    @Override
    public String prefixImage(String path) {
        // 如果那些域名不需要加，则跳过
        return path.replace(Constants.UPLOAD_TYPE_IMAGE+"/", getCdnUrl() + "/"+ Constants.UPLOAD_TYPE_IMAGE+"/");
    }

    /**
     * 给文件加前缀
     * @param path String 路径
     * @return String
     */
    @Override
    public String prefixFile(String path) {
        if (path.contains("file/excel")) {
            String cdnUrl = systemConfigService.getValueByKey("local" + "UploadUrl");
            return path.replace("file/", cdnUrl + "/file/");
        }
        return path.replace("file/", getCdnUrl() + "/file/");
    }

    @Override
    public String clearPrefix(String path) {
        return "";
    }

    /**
     * 获取cdn url
     * @return String
     */
    private String getCdnUrl() {
//        return asyncService.getCurrentBaseUrl();
        String uploadType = systemConfigService.getValueByKeyException("uploadType");
        //获取配置信息
        int type = Integer.parseInt(uploadType);
        String pre = "local";
        switch (type) {
            case 2:
                pre = "qn";
                break;
            case 3:
                pre = "al";
                break;
            case 4:
                pre = "tx";
                break;
            default:
                break;
        }
        return systemConfigService.getValueByKey(pre+"UploadUrl");
    }



}

