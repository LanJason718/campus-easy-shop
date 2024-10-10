package com.jason.front.controller;


import com.jason.common.model.user.User;
import com.jason.common.utils.ResultUtil;
import com.jason.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/front/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResultUtil register(@RequestBody User user) {
        if(userService.save(user)){
            return ResultUtil.success("新增成功");
        }
        return ResultUtil.error("新增失败");
    }
}
