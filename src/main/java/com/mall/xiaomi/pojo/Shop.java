package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "`shop`")
@ApiModel(value = "商家")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    @ApiModelProperty(value = "商家id")
    private Integer Id;
    @ApiModelProperty(value = "商家名")
    private String username;
    @ApiModelProperty(value = "商家密码")
    private String password;

}
