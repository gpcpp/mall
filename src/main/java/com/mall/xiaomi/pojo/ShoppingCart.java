package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "shopping_cart")
@ApiModel(description = "购物车")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @ApiModelProperty(value = "购物车id")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "商品id")
    private Integer productId;
    @ApiModelProperty(value = "商品数量")
    private Integer num;
}
