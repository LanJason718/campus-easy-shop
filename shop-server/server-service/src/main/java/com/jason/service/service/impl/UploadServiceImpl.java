package com.jason.service.service.impl;

import cn.hutool.core.util.StrUtil;
import com.jason.common.config.ShopConfig;
import com.jason.common.constants.Constants;
import com.jason.common.exception.ShopException;
import com.jason.common.model.system.SystemAttachment;
import com.jason.common.utils.ShopUtil;
import com.jason.common.utils.DateUtil;
import com.jason.common.utils.UploadUtil;
import com.jason.common.vo.CloudVo;
import com.jason.common.vo.FileResultVo;
import com.jason.common.vo.UploadCommonVo;
import com.jason.service.service.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class UploadServiceImpl implements UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private SystemAttachmentService systemAttachmentService;

    @Autowired
    private OssService ossService;

    @Autowired
    ShopConfig shopConfig;

    /**
     * 图片上传
     *
     * @param multipartFile 文件
     * @param model         模块 用户user,商品product,微信wechat,news文章
     * @param pid           分类ID 0编辑器,1商品图片,2拼团图片,3砍价图片,4秒杀图片,5文章图片,6组合数据图,7前台用户,8微信系列
     * @return FileResultVo
     */
    @Override
    public FileResultVo imageUpload(MultipartFile multipartFile, String model, Integer pid) throws IOException {
        // 判断文件是否为空
        if (null == multipartFile || multipartFile.isEmpty()) {
            throw new ShopException("上传的文件对象不存在...");
        }

        // 获取配置中的根目录路径，去掉空格和重复的斜杠
        String rootPath = shopConfig.getImagePath().replace(" ", "").replace("//", "/");

        // 设置上传的模型路径（文件所属模块的路径）
        UploadUtil.setModelPath(model);
        String modelPath = "public/" + model + "/";

        // 获取允许的文件扩展名和文件大小
        String extStr = systemConfigService.getValueByKey(Constants.UPLOAD_IMAGE_EXT_STR_CONFIG_KEY);
        int size = Integer.parseInt(systemConfigService.getValueByKey(Constants.UPLOAD_IMAGE_MAX_SIZE_CONFIG_KEY));
        String type = Constants.UPLOAD_TYPE_IMAGE + "/";

        // 构建上传通用配置对象
        UploadCommonVo uploadCommonVo = new UploadCommonVo();
        uploadCommonVo.setRootPath(rootPath);
        uploadCommonVo.setModelPath(modelPath);
        uploadCommonVo.setExtStr(extStr);
        uploadCommonVo.setSize(size);
        uploadCommonVo.setType(type);

        // 获取文件名和扩展名
        String fileName = multipartFile.getOriginalFilename();
        String extName = FilenameUtils.getExtension(fileName);

        // 判断扩展名是否存在
        if (StringUtils.isEmpty(extName)) {
            throw new RuntimeException("文件类型未定义不能上传...");
        }

        // 文件名过长时，截取前90个字符，添加扩展名
        if (fileName.length() > 99) {
            fileName = StrUtil.subPre(fileName, 90).concat(".").concat(extName);
        }

        // 验证文件大小是否超出允许范围
        float fileSize = (float) multipartFile.getSize() / 1024 / 1024;
        String fs = String.format("%.2f", fileSize);
        if (fileSize > uploadCommonVo.getSize()) {
            throw new ShopException("最大允许上传" + uploadCommonVo.getSize() + " MB的文件, 当前文件大小为 " + fs + " MB");
        }

        // 验证文件扩展名是否符合配置要求
        if (StringUtils.isNotEmpty(uploadCommonVo.getExtStr())) {
            List<String> extensionList = ShopUtil.stringToArrayStr(uploadCommonVo.getExtStr());
            if (extensionList.size() > 0) {
                if (!extensionList.contains(extName)) {
                    throw new ShopException("上传文件的类型只能是：" + uploadCommonVo.getExtStr());
                }
            } else {
                throw new ShopException("上传文件的类型只能是：" + uploadCommonVo.getExtStr());
            }
        }

        // 生成新的文件名
        String newFileName = UploadUtil.fileName(extName);

        // 构建文件的存储路径
        String webPath = uploadCommonVo.getType() + uploadCommonVo.getModelPath() + DateUtil.nowDate(Constants.DATE_FORMAT_DATE).replace("-", "/") + "/";
        String destPath = FilenameUtils.separatorsToSystem(uploadCommonVo.getRootPath() + webPath) + newFileName;

        // 创建存储文件
        File file = UploadUtil.createFile(destPath);

        // 构建上传结果对象 FileResultVo
        FileResultVo resultFile = new FileResultVo();
        resultFile.setFileSize(multipartFile.getSize());
        resultFile.setFileName(fileName);
        resultFile.setExtName(extName);
        resultFile.setUrl(webPath + newFileName);
        resultFile.setType(multipartFile.getContentType());

        // 获取上传类型（1 本地，2 OSS等）
        String uploadType = systemConfigService.getValueByKeyException("uploadType");
        Integer uploadTypeInt = Integer.parseInt(uploadType);
        CloudVo cloudVo = new CloudVo();

        // 设置系统附件信息（存储文件信息到数据库）
        resultFile.setType(resultFile.getType().replace("image/", ""));
        SystemAttachment systemAttachment = new SystemAttachment();
        systemAttachment.setName(resultFile.getFileName());
        systemAttachment.setSattDir(resultFile.getUrl());
        systemAttachment.setAttSize(resultFile.getFileSize().toString());
        systemAttachment.setAttType(resultFile.getType());
        systemAttachment.setImageType(1);   // 图片上传类型 1本地，2七牛云，3OSS等
        systemAttachment.setPid(pid);

        // 判断上传类型并处理文件
        if (uploadTypeInt.equals(1)) {
            // 本地存储文件
            multipartFile.transferTo(file);
            systemAttachmentService.save(systemAttachment);
            return resultFile;
        }

        // 判断是否需要保存本地文件
        String fileIsSave = systemConfigService.getValueByKeyException("file_is_save");
        multipartFile.transferTo(file);  // 文件传输到本地

        switch (uploadTypeInt) {
            case 2:  // OSS 上传
                systemAttachment.setImageType(3);
                String pre = "al";  // 假设OSS使用的是阿里云
                cloudVo.setDomain(systemConfigService.getValueByKeyException(pre + "UploadUrl"));
                cloudVo.setAccessKey(systemConfigService.getValueByKeyException(pre + "AccessKey"));
                cloudVo.setSecretKey(systemConfigService.getValueByKeyException(pre + "SecretKey"));
                cloudVo.setBucketName(systemConfigService.getValueByKeyException(pre + "StorageName"));
                cloudVo.setRegion(systemConfigService.getValueByKeyException(pre + "StorageRegion"));
                try {
                    // 上传到OSS
                    String webPathAl = shopConfig.getImagePath();
                    logger.info("AsyncServiceImpl.oss.id " + systemAttachment.getAttId());
                    ossService.upload(cloudVo, systemAttachment.getSattDir(), webPathAl + "/" + systemAttachment.getSattDir(), file);
                } catch (Exception e) {
                    logger.error("AsyncServiceImpl.oss fail " + e.getMessage());
                }
                break;
        }

        // 保存附件信息到数据库
        systemAttachmentService.save(systemAttachment);

        // 根据配置决定是否删除本地文件
        if (!"1".equals(fileIsSave)) {
            file.delete();  // 删除本地文件
        }

        return resultFile;
    }
}
