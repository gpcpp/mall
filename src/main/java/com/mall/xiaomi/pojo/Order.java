package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "`order`")
@ApiModel(description = "订单")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "订单号")
    private String orderId;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "商品id")
    private Integer productId;
    @ApiModelProperty(value = "商品数量")
    private Integer productNum;
    @ApiModelProperty(value = "商品价格")
    private Double productPrice;
    @ApiModelProperty(value = "订单状态")
    private Long orderTime;

}
