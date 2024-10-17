package com.jason.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.common.model.system.SystemAdmin;
import com.jason.service.dao.SystemAdminDao;
import com.jason.service.service.SystemAdminService;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminDao, SystemAdmin> implements SystemAdminService {
}
