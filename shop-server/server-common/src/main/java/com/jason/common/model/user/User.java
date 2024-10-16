package com.jason.common.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
@TableName("user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    @TableId
    private int uid;   // 用户id
    private String openId;
    private String username;  // 账号
    private String avatar;
    private String password;  // 密码

    private String nickname; //昵称

    @ApiModelProperty(value = "1为正常，0为禁止")
    private Boolean status;
    private String phone;     // 手机号

    private String gender;    // 性别


    private String role;      // 角色 0：普通用户 1：管理员

    private Timestamp createTime; // 创建时间

    private Timestamp updateTime; // 更新时间
}