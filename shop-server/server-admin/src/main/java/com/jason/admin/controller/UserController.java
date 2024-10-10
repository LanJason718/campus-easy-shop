package com.jason.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.admin.entity.PageParm;
import com.jason.admin.entity.User;
import com.jason.admin.service.UserService;
import com.jason.utils.ResultUtils;
import com.jason.utils.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    //新增
    @PostMapping("register")
    public ResultVo add(@RequestBody User user){
        if(userService.save(user)){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody User user){
        if(userService.updateById(user)){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId){
        if(userService.removeById(userId)){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    // 列表查询
    @GetMapping("/getList")
    public ResultVo getList(PageParm parm) {
        // 构造查询条件
        QueryWrapper<User> query = new QueryWrapper<>();

        // 如果昵称不为空，添加模糊查询条件
        query.lambda().like(StringUtils.isNotEmpty(parm.getNickName()), User::getNickName, parm.getNickName());

        // 构造分页对象，IPage 用于封装分页信息
        IPage<User> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());

        // 执行查询
        IPage<User> list = userService.page(page, query);

        // 返回查询结果
        return ResultUtils.success("查询成功", list);
    }
}
