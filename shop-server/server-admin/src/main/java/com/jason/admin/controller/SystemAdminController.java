package com.jason.admin.controller;

import com.jason.common.model.system.SystemAdmin;
import com.jason.common.response.CommonResult;
import com.jason.service.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/system/admin")
public class SystemAdminController {

    @Autowired
    private SystemAdminService systemAdminService;

    @PostMapping("add")
    public CommonResult add(@RequestBody SystemAdmin systemAdmin){
        systemAdminService.save(systemAdmin);
        return CommonResult.success("新增管理员成功");
    }
}
