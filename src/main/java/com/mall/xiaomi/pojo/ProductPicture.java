package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "product_picture")
@ApiModel(description = "商品图片")
public class ProductPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @ApiModelProperty(value = "商品图片id")
    private Integer id;
    @ApiModelProperty(value = "商品id")
    private Integer productId;
    @ApiModelProperty(value = "商品图片地址")
    private String productPicture;
    @ApiModelProperty(value = "商品图片介绍")
    private String intro;

}
