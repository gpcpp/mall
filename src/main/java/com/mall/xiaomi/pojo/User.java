package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "`user`")

@ApiModel(value = "用户")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;

}
