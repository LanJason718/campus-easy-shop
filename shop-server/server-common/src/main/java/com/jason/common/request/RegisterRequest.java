package com.jason.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RegisterRequest", description = "用户注册")
public class RegisterRequest {
    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickName;

    @ApiModelProperty(value = "微信小程序用户头像", required = true)
    private String avatar;

    @ApiModelProperty(value = "注册key")
    private String key;
}
