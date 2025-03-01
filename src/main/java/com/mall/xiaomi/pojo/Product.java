package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "product")
@ApiModel(description = "商品信息")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @ApiModelProperty(value = "商品id")
    private Integer productId;
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "商品分类id")
    private Integer categoryId;
    @ApiModelProperty(value = "商品标题")
    private String productTitle;
    @ApiModelProperty(value = "商品图片")
    private String productPicture;
    @ApiModelProperty(value = "商品价格")
    private Double productPrice;
    @ApiModelProperty(value = "商品售价")
    private Double productSellingPrice;
    @ApiModelProperty(value = "商品库存")
    private Integer productNum;
    @ApiModelProperty(value = "商品销量")
    private Integer productSales;
    @ApiModelProperty(value = "商品详情")
    private String productIntro;

}
