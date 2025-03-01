package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "collect")
@ApiModel(description = "收藏表")
public class Collect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @ApiModelProperty(value = "收藏id")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "商品id")
    private Integer productId;
    @ApiModelProperty(value = "收藏时间")
    private Long collectTime;

}
