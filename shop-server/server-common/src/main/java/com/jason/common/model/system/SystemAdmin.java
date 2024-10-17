package com.jason.common.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台管理员表
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_admin")
@ApiModel(value="SystemAdmin对象", description="后台管理员表")
public class SystemAdmin implements Serializable {

    @ApiModelProperty(value = "后台管理员表ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "后台管理员账号")
    private String username;

    @ApiModelProperty(value = "后台管理员密码")
    private String password;

    @ApiModelProperty(value = "后台管理员姓名")
    private String realName;


    @ApiModelProperty(value = "后台管理员性别")
    private String gender;

    @ApiModelProperty(value = "后台管理员昵称")
    private String nickname;

    @ApiModelProperty(value = "后台管理员角色")
    private String role;

    @ApiModelProperty(value = "后台管理员修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "后台管理员添加时间")
    private Date createTime;

    @ApiModelProperty(value = "后台管理员状态 1有效 0无效")
    private Boolean status;

    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;

    @ApiModelProperty(value = "手机号码")
    private String phone;

}
